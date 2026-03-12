package utils;

import java.util.Map;
import java.util.HashMap;

public class TestContext {
    private static final ThreadLocal<Map<String, Object>> context =
            ThreadLocal.withInitial(HashMap::new);

    private TestContext() {}

    public static void set(String key, Object value) {
        context.get().put(key, value);
    }

    public static <T> T get(String key) {
        return (T) context.get().get(key);
    }

    public static void clear() {
        context.get().clear();
    }
}

/*
Store any value from any API
 */