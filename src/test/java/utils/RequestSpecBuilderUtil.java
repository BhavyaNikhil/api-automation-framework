package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {
    private static RequestSpecification requestSpec;
    public static RequestSpecification getRequestSpec(){
//        requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
//                .addHeader("Authorization", "Bearer " + TokenManager.getToken())
//                .build();
//        return requestSpec;

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", "Bearer " + TokenManager.getToken());

        // ⭐ THIS LINE ENABLES AUTO REQUEST + RESPONSE IN ALLURE
        builder.addFilter(new AllureRestAssured());

        return builder.build();
    }
}