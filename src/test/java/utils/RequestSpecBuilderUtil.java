package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {
    private static RequestSpecification requestSpec;
    public static RequestSpecification getRequestSpec(){
        requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + TokenManager.getToken())
                .build();
        return requestSpec;
    }
}