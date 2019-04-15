package javaDetails.reflext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:wuhao
 * @description:数据库操作
 * @date:2019/4/15
 */
public class SqlSession {
    public static String getSaveObjectSql(Object o) throws InvocationTargetException, IllegalAccessException {
        String sql = "insert into";

        // 获取Class对象
        Class c = o.getClass();

        // 获取所有Class所有的方法
        Method[] methods = c.getDeclaredMethods();

        // 获取全类名
        String cName = c.getName();

        //通过全类名获取数据库名称
        String tableName = cName.substring(cName.lastIndexOf(".") + 1, cName.length());

        sql += tableName + "(";

        // 字段名字
        List<String> fieldList = new ArrayList<>();

        //字段对应的值
        List valueList = new ArrayList();

        for (Method method : methods) {
            String methodName = method.getName();
            //找出get方法，并设置值
            if (methodName.startsWith("get") &&! method.equals("getClass")) {
                String fieldName = methodName.substring(3, methodName.length());
                fieldList.add(fieldName);

                Object res = method.invoke(o, null);
                if (res instanceof String) {
                    valueList.add("\"" + res + "\"");
                } else {
                    valueList.add(res);
                }
            }
        }

        //拼接sql语句的字段
        for (int i = 0; i < fieldList.size(); i++) {
            if (i < fieldList.size() - 1) {
                sql += fieldList.get(i) + ",";
            } else {
                sql += fieldList.get(i) + ") values (";
            }
        }

        // 拼接sql语句值
        for (int i = 0; i < valueList.size(); i++) {
            if (i < valueList.size() - 1) {
                sql += valueList.get(i) + ",";
            } else {
                sql += valueList.get(i) + ")";
            }
        }

        return sql;
    }


    /**
     * 保存数据的操作
     * @param o
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public int saveObject(Object o) throws InvocationTargetException, IllegalAccessException, SQLException {
        Connection connection = ConnectDBFactory.getDBConnection();
        String sql = getSaveObjectSql(o);
        PreparedStatement statement = connection.prepareStatement(sql);
        int i = 0;
        i = statement.executeUpdate();
        return i;
    }

    /**
     * 询数据，查询出来的数据映射到pojo的每一个属性上
     * @param pName
     * @param id
     * @return
     * @throws ClassNotFoundException
     */
   public Object getObject(String pName,int id) throws ClassNotFoundException {
       String tableName = pName.substring(pName.lastIndexOf(".") + 1, pName.length());
       String sql = "select * from " + tableName + " where Id = " + id;
       Connection conn = ConnectDBFactory.getDBConnection();
       Class c = Class.forName(pName);

       Object obj = null;

       Statement statement = null;
       try {
           statement = conn.createStatement();
           ResultSet resultSet = statement.executeQuery(sql);
           Method[] methods = c.getDeclaredMethods();

           while (resultSet.next()) {
               obj = c.newInstance();
               for (Method method : methods) {
                   String methodName = method.getName();
                   if (methodName.startsWith("set")) {
                       //通过方法名获取数据库的列名
                       String columnName = methodName.substring(3, methodName.length());
                       //获取参数的类型
                       Class[] params = method.getParameterTypes();

                       //判断参数类型
                       if (params[0] == String.class) {
                           method.invoke(obj, resultSet.getString(columnName));
                       }
                       if(params[0] == int.class){
                           method.invoke(obj,resultSet.getInt(columnName));
                       }
                   }
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
               e.printStackTrace();
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }
       return obj;
   }

    public static void main(String[] args) {
        SqlSession session = new SqlSession();
        User user = new User();
        user.setAge(22);
        user.setName("Hulk");
        user.setId(32);
        user.setPwd("123456");
        try {
            //插入数据
            int resNum = session.saveObject(user);
            if(resNum > 0){
                System.out.println("成功");
            }else{
                System.out.println("插入失败");
            }
            // 查询数据
            User res = (User)session.getObject("dbtest.User",44);
            System.out.println(res);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
