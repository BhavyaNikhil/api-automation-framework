package api;

import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PostWhitelistandAddCarAPI {
    public static Response postWhitelistandAddCar(Object requestBody) {
        return given()
                .log().all() //logs request
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .body(requestBody)
                .when()
                .post(Endpoints.POST_WHITELIST_AND_ADD_CAR)
                .then()
                .log().all() //logs response
                .extract().response();
    }
}
