package api;

import base.BaseTest;
import config.ConfigManager;
import config.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.TripReportbyUsernameRequest;
import org.testng.annotations.Test;
import utils.RequestSpecBuilderUtil;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;
import utils.TokenType;

import static io.restassured.RestAssured.given;

public class HardDeleteDeviceAPI {
    public static Response hardDeleteDevice(String imei){
        return given()
                .spec(RequestSpecBuilderUtil.getRequestSpec(TokenType.SUPERUSER))
                .pathParam("imei",imei)
                .log().all()
                .when()
                .delete(Endpoints.HARD_DELETE_DEVICE)
                .then()
                .log().ifValidationFails()
                .extract().response();
    }
}