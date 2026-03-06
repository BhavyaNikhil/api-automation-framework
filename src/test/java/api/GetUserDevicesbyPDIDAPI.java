package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetUserDevicesbyPDIDAPI {
    public static Response getUserDevicesbyPDID(String username, String pdid) {
        Response response = given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("username", username)
                .pathParam("pdid", pdid)
                .when()
                .get(Endpoints.GET_USER_DEVICES_BY_PDID)
                .then()
                .extract().response();
        //Attach response to Allure
        AllureUtil.attachResponse(response.asString());
        return response;
    }
}