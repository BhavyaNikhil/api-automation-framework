package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetVehicleSystemStatusAPI {
    public static Response getVehicleSystemStatus(String pdid) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("pdid", pdid)
                .when()
                .get(Endpoints.GET_VEHICLE_SYSTEM_STATUS)
                .then()
                .extract().response();
    }
}
