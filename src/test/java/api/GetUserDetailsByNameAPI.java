package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;
import utils.TokenType;

import static io.restassured.RestAssured.given;

public class GetUserDetailsByNameAPI {
    public static Response getUserByName(String username) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec(TokenType.JHSTENANTADMIN))
                .pathParam("username_jhs", username)
                .when()
                .get(Endpoints.GET_USER_DETAILS_BY_NAME)
                .then()
                .extract().response();
    }
}