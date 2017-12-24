package hello.core;

public class Helpers {

    public static Object getValueOrDefault(Object value, Object defaultValue) {
        return value == null ? defaultValue : value;
    }

}
