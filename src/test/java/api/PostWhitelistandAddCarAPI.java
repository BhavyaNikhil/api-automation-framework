package api;

import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.WhitelistAddCarRequest;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;
import utils.TokenType;

import static io.restassured.RestAssured.given;

public class PostWhitelistandAddCarAPI {
    public static Response postWhitelistandAddCar(WhitelistAddCarRequest requestBody) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec(TokenType.JHSTENANTADMIN))
                .body(requestBody)
                .log().all()
                .when()
                .post(Endpoints.POST_WHITELIST_AND_ADD_CAR)
                .then()
                .extract().response();
    }
}
