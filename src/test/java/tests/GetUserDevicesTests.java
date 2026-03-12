package tests;

import api.GetUserDevicesAPI;
import api.GetVehicleSystemStatusAPI;
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
import utils.AllureUtil;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;
import utils.SchemaValidatorUtil;

public class GetUserDevicesTests extends BaseTest {
    @Feature("User Devices by Username API")
    @Story("Get User Devices by Username API")
    @Description("Verify user devices are displayed based on username")
    @Test
    public void verifyGetUserDevicesContract() {

        String userID = ConfigManager.getProperty("username_jhs");

        Response response = RetryUtil.executeWithRetry(() ->
                GetUserDevicesAPI.getUserDevices(userID),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetUserDevicesAPISchema.json");
    }
}
