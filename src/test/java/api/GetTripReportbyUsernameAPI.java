package api;

import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.TripReportbyUsernameRequest;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;
import utils.TokenType;

import static io.restassured.RestAssured.given;

public class GetTripReportbyUsernameAPI {
    public static Response postTripReportbyUsername(String username, TripReportbyUsernameRequest requestBody) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec(TokenType.JHSTENANTADMIN))
                .log().all()
                .pathParam("username_jhs", username)
                .body(requestBody)
                .when()
                .post(Endpoints.GET_TRIP_REPORT_BY_USERNAME)
                .then()
                .log().ifValidationFails()
                .extract().response();
    }
}
