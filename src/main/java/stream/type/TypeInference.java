package stream.type;

/**
 * @author:wuhao
 * @description:类型接口
 * @date:18/12/3
 */
public class TypeInference {
    public static class Value<T> {
        public static <T> T defaultValue() {
            return null;
        }

        public T getOrDefault(T value, T defaultValue) {
            return (value != null) ? value : defaultValue;
        }
    }


    public static void main(String[] args) {
        final Value<String> value = new Value<>();
        value.getOrDefault("22", Value.defaultValue());
    }
}
