package api;

import config.ConfigManager;
import config.Endpoints;
import io.restassured.response.Response;
import utils.RequestSpecBuilderUtil;

import static io.restassured.RestAssured.given;

public class GetAccessTokenAPI {
    public static Response getAccessToken() {

        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", ConfigManager.getProperty("username"))
                .formParam("password", ConfigManager.getProperty("password"))
                .formParam("grant_type", ConfigManager.getProperty("grant_type"))
                .formParam("client_id", ConfigManager.getProperty("client_id"))
                .formParam("client_secret", ConfigManager.getProperty("client_secret"))
                .when()
                .post(Endpoints.GET_ACCESS_TOKEN)
                .then()
                .extract().response();
    }
}

//Token API Class