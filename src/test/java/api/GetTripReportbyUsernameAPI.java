package api;

import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetTripReportbyUsernameAPI {
    public static Response postTripReportbyUsername(String username, Object requestBody) {
        //Attach request to Allure
        AllureUtil.attachRequest(requestBody.toString());

        Response response = given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("username", username)
                .body(requestBody)
                .when()
                .post(Endpoints.GET_TRIP_REPORT_BY_USERNAME)
                .then()
                .extract().response();
        //Attach response to Allure
        AllureUtil.attachResponse(response.asString());
        return response;
    }
}
