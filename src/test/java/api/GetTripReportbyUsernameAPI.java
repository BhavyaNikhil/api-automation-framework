package api;

import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetTripReportbyUsernameAPI {
    public static Response postTripReportbyUsername(String username, Object requestBody) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("username", username)
                .body(requestBody)
                .when()
                .post(Endpoints.GET_TRIP_REPORT_BY_USERNAME)
                .then()
                .extract().response();
    }
}
