package hello.core;

public final class Functions {

    public static String nl2br(String string) {
        return (string != null) ? string.replace("\n", "<br/>") : null;
    }

}