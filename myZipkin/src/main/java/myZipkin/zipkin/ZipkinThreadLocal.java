package myZipkin.zipkin;

import java.util.HashMap;
import java.util.Map;

public class ZipkinThreadLocal {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>() {
        protected Map<String, Object> initialValue() {
            return new HashMap();
        }
    };
    public static String KEY_CONTROLLER_ARGUMENTS = "KEY_CONTROLLER_ARGUMENTS";

    public ZipkinThreadLocal() {
    }

    public static void setControllerArguments(Object[] args) {
        ((Map)threadLocal.get()).put(KEY_CONTROLLER_ARGUMENTS, args);
    }

    public static Object[] getControllerArguments() {
        return (Object[])((Object[])((Map)threadLocal.get()).get(KEY_CONTROLLER_ARGUMENTS));
    }
}
