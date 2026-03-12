package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;
import utils.TokenType;

import static io.restassured.RestAssured.given;

public class GetUserDevicesbyPDIDAPI {
    public static Response getUserDevicesbyPDID(String username, String pdid) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec(TokenType.JHSTENANTADMIN))
                .pathParam("username_jhs", username)
                .pathParam("pdid", pdid)
                .when()
                .get(Endpoints.GET_USER_DEVICES_BY_PDID)
                .then()
                .extract().response();
    }
}