package utils;

import io.restassured.response.Response;

import java.util.List;

public class ResponseUtil {

    private ResponseUtil() {}

    public static String getString(Response response, String path) {
        return response.jsonPath().getString(path);
    }

    public static Integer getInt(Response response, String path) {
        return response.jsonPath().getInt(path);
    }

    public static Long getLong(Response response, String path) {
        return response.jsonPath().getLong(path);
    }

    public static Boolean getBoolean(Response response, String path) {
        return response.jsonPath().getBoolean(path);
    }

    public static <T> List<T> getList(Response response, String path) {
        return response.jsonPath().getList(path);
    }

    public static void store(Response response, String jsonPath, String key) {
        Object value = response.jsonPath().get(jsonPath);
        TestContext.set(key, value);
    }

    public static void print(Response response) {
        response.prettyPrint();
    }
}