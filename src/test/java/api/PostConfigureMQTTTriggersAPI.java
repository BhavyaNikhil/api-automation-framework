package api;

import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RequestSpecBuilderUtil;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class PostConfigureMQTTTriggersAPI {
    public static Response postConfigureMQTTTriggers(String pdid, Object requestBody) {
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec())
                .pathParam("pdid", pdid)
                .body(requestBody)
                .when()
                .post(Endpoints.POST_CONFIGURE_MQTT_TRIGGERS)
                .then()
                .extract().response();
    }
}
