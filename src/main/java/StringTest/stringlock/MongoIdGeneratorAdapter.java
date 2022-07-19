package StringTest.stringlock;


import com.ai.appframe2.mongodb.MongoDBConstants;
import com.ai.appframe2.mongodb.connection.MongoDBConnection;
import com.ai.appframe2.transaction.dbconnmanager.DBConnection;
import com.sun.deploy.services.ServiceManager;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:wuhao
 * @description:间接对String加锁(保证分布式ID不重复)
 * @date:18/8/8
 */
public class MongoIdGeneratorAdapter {


    //每次获取缓存大小
    private static int CACHE_SIZE = 2000;
    private static Map<String, BatchSequence> SEQ_MAP = new HashMap<>();

    /**
     * 由于String对象的特殊性，因此为了不在String对象上加锁，才构造了此Map
     */
    private static ConcurrentHashMap<String, Object> LOCK_MAP = new ConcurrentHashMap<>();

    public MongoIdGeneratorAdapter() {
    }


    public static long getNewId(String collectionName) {
        Object lock = getLock(collectionName);

        BatchSequence seq = null;

        synchronized (lock) {
            seq = SEQ_MAP.get(collectionName);
            //在多线程环境下，要保证new BatchSequence只会执行一次
            if (seq == null) {
                SEQ_MAP.put(collectionName, new BatchSequence(collectionName));
                seq = SEQ_MAP.get(collectionName);
            }
        }

        long id = 0;
        try {
            id = seq.getNewId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    /**
     * 获取锁对象
     * 该方法实际上实现了对SEQ_MAP按照collectionName加锁,提高并发吞吐量
     *
     * @param collectionName
     * @return
     */
    private static Object getLock(String collectionName) {
        Object lock = LOCK_MAP.get(collectionName);
        Object mid = null;
        if (lock == null) {
            mid = new Object();
            lock = LOCK_MAP.putIfAbsent(collectionName, mid);
        }

        if (lock == null) {
            lock = mid;
        }

        return lock;
    }


    public static class BatchSequence {
        private long currentId;
        private int used;
        private String collectionName;

        public BatchSequence(String collectionName) {
            this.collectionName = collectionName;
        }

        private void setCurrentId() throws Exception {
//            Connection aConn = ServiceManager.getService().getConnection();
//            // 查询MongoDB获取到DBCursor
//            DB db = null;
//            if (aConn instanceof MongoDBConnection) {
//                db = aConn.getMongoDatabase();
//            }
//            DBConnection coll = db.getColllection(MongoDBConstants.IDGenerator.ID_TABLE_NAME);
//            //查询，并递增CACHESIZE大小
//            DBObject object1 = coll.findAndModify(new BasicDBObject(MongoDBConstants.IDGenerator.COLLECTION_NAME, this.collectionName),new BasicDBObject("$inc", new BasicDBObject(MongoDBConstants.IDGenerator.SEQUENCE, CACHESIZE)));
//
//            // 该情况，表示数据库中已经有该表的sequence记录,大部分情况下都走该分支
//            if (object1 != null) {
//                this.used = 0;
//                this.currentId = object1.get(MongoDBConstants.IDGenerator.SEQUENCE);
//                return;
//            }
//            // 数据库中还没有该表的sequence记录，需要先插入一条记录，sequence从1开始
//            insert(coll);
//
//            //分布式环境下，多个进程同时往数据库中写入同一条记录时，只有一个会成功，但是我们不关注，因为插入完成后，我们重新查询一次
//            DBObject object2 = coll.findAndModify(new BasicDBObject(MongoDBConstants.IDGenerator.COLLECTION_NAME, this.collectionName),
//                    new BasicDBObject("$inc", new BasicDBObject(MongoDBConstants.IDGenerator.SEQUENCE, CACHE_SIZE)));
//
//            if (object2 != null) {
//                this.used = 0;
//                this.currentId = object2.get(MongoDBConstants.IDGenerator.SEQUENCE);
//                return;
//            } else {
//                throw new Exception("unexcepted error occurs.");
//            }

        }

//        private void insert(DBCollection coll) {
//            //往表中插入一条数据，sequence从1开始
//            long start = 1L;
//            //将_id设置为collectionName，确保分布式环境下也只会存在一条记录
//            BasicDBObject doc = new BasicDBObject("_id", this.collectionName)
//                    .append(MongoDBConstants.IDGenerator.COLLECTION_NAME, this.collectionName)
//                    .append(MongoDBConstants.IDGenerator.SEQUENCE, start);
//
//            coll.insert(doc,WriteConcern.ACKNOWLEDGED);
//        }

        /**
         * 同步方法
         *
         * @return
         * @throws Exception
         */
        public synchronized long getNewId() throws Exception {
            //缓存已经用完，重新获取
            if (this.used == CACHE_SIZE) {
                setCurrentId();
            }

            this.used++;
            return this.currentId++;
        }
    }

}
