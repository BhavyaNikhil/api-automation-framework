package tests;

import api.GetUserDevicesAPI;
import api.GetUserDevicesbyPDIDAPI;
import base.BaseTest;
import config.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;
import utils.SchemaValidatorUtil;

public class GetUserDevicesbyPDIDTests extends BaseTest {
    @Test
    public void verifyGetUserDevicesbyPDIDContract() {

        String userID = ConfigManager.getProperty("username");
        String pdId = ConfigManager.getProperty("pdid");

        Response response = RetryUtil.executeWithRetry(() ->
                GetUserDevicesbyPDIDAPI.getUserDevicesbyPDID(userID,pdId),3);
        System.out.println(response.getBody().asString());

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetUserDevicesbyPDIDAPISchema.json");
    }
}
