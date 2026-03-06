package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class AllureUtil {
//
//    @Attachment(value = "{name}", type = "application/json")
//    public static String attachJson(String name, String json) {
//        return json;
//    }
//
//    public static void attachRequestResponse(String request, String response) {
//        attachJson("Request", request);
//        attachJson("Response", response);
//    }
    @Attachment(value = "Request Body", type = "application/json")
    public static String attachRequest(String request){
        return request;
    }

    @Attachment(value = "Response Body", type = "application/json")
    public static String attachResponse(String response){
        return response;
    }
}