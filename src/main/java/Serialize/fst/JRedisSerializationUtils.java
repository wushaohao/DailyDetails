package Serialize.fst;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import de.ruedigermoeller.serialization.FSTConfiguration;

import java.io.*;

/**
 * @author:wuhao
 * @description:序列化工具类
 * @date:18/4/17
 */
public class JRedisSerializationUtils {

    public JRedisSerializationUtils() {
    }

    /**
     * FST序列化方式
     */
    static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

    public static byte[] fstSerialize(Object obj) {
        return configuration.asByteArray((Serializable) obj);
    }

    public static Object fstDeserialize(byte[] bytes) {
        return configuration.asObject(bytes);
    }


    /**
     * Kryo序列化方式
     *
     * @param obj
     * @return
     */
    public static byte[] kryoSerizlize(Object obj) {
        Kryo kryo = new Kryo();
        byte[] buffer = new byte[2048];

        try (Output output = new Output(buffer)) {
            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        } catch (Exception e) {

        }
        return buffer;
    }


    /**
     * Kryo 反序列化
     *
     * @param bytes
     * @return
     */
    static Kryo kryo = new Kryo();

    public static Object kryoDeSerialize(byte[] bytes) {
        try (Input input = new Input(bytes)) {
            return kryo.readClassAndObject(input);
        } catch (Exception e) {

        }
        return kryo;
    }


    /**
     * jdk原生序列化
     *
     * @param obj
     * @return
     */
    public static byte[] jdkSerialize(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)
        ) {
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * jdk原生反序列化
     *
     * @param bytes
     * @return
     */
    public static Object jdkDeSerialize(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)
        ) {
            return ois.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
