package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RequestSpecBuilderUtil {
    public static RequestSpecification getRequestSpec(TokenType tokenType){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization","Bearer " + TokenManager.getToken(tokenType));
        headers.put("Content-Type", "application/json");
        builder.addHeaders(headers);

        // ⭐ THIS LINE ENABLES AUTO REQUEST + RESPONSE IN ALLURE
        builder.addFilter(new AllureFilterUtil());

        return builder.build();
    }
}