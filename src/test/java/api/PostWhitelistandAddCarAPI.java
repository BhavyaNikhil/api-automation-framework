package api;

import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.AllureUtil;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PostWhitelistandAddCarAPI {
    public static Response postWhitelistandAddCar(Object requestBody) {
        //Attach request to Allure
        AllureUtil.attachRequest(requestBody.toString());

        Response response = given()
                .log().all() //logs request
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .body(requestBody)
                .when()
                .post(Endpoints.POST_WHITELIST_AND_ADD_CAR)
                .then()
                .log().all() //logs response
                .extract().response();
        //Attach response to Allure
        AllureUtil.attachResponse(response.asString());
        return response;
    }
}
