package tests;

import api.GetUserDevicesAPI;
import api.GetUserDevicesbyPDIDAPI;
import base.BaseTest;
import config.ConfigManager;
import config.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.*;

public class GetUserDevicesbyPDIDTests extends BaseTest {
    @Feature("User Devices by PDID API")
    @Story("Get User Devices by PDID API")
    @Description("Verify user devices are displayed based on pdid")
    @Test
    public void verifyGetUserDevicesbyPDIDContract() {

        String userID = ConfigManager.getProperty("username_jhs");
        String pdId = TestContext.get("PD_ID");

        Response response = RetryUtil.executeWithRetry(() ->
                GetUserDevicesbyPDIDAPI.getUserDevicesbyPDID(userID,pdId),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetUserDevicesbyPDIDAPISchema.json");
    }
}
