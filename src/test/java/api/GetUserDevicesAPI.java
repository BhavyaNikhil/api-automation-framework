package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;
import utils.TokenType;

import static io.restassured.RestAssured.given;

public class GetUserDevicesAPI {
    public static Response getUserDevices(String username) {

        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec(TokenType.JHSTENANTADMIN))
                .pathParam("username_jhs", username)
                .log().all()
                .when()
                .get(Endpoints.GET_USER_DEVICES)
                .then()
                .log().all()
                .extract().response();
    }
}
