package api;

import config.Endpoints;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class GetUserDetailsByNameAPI {
    public static Response getUserByName(String username) {
        //Attach request to Allure
        Response response = given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("username", username)
                .when()
                .get(Endpoints.GET_USER_DETAILS_BY_NAME)
                .then()
                .extract().response();

        //Attach response to Allure
        AllureUtil.attachResponse(response.asString());
        return response;
    }
}