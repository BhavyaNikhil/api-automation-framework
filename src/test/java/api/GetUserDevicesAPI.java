package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetUserDevicesAPI {
    public static Response getUserDevices(String username) {

        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("username", username)
                .when()
                .get(Endpoints.GET_USER_DEVICES)
                .then()
                .extract().response();
    }
}
