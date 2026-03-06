package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetVehicleSystemStatusAPI {
    public static Response getVehicleSystemStatus(String pdid) {
        Response response = given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("pdid", pdid)
                .when()
                .get(Endpoints.GET_VEHICLE_SYSTEM_STATUS)
                .then()
                .extract().response();
        //Attach response to Allure
        AllureUtil.attachResponse(response.asString());
        return response;
    }
}
