package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetUserDetailsByNameAPI {
    public static Response getUserByName(String username) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("username", username)
                .when()
                .get(Endpoints.GET_USER_DETAILS_BY_NAME)
                .then()
                .extract().response();
    }
}