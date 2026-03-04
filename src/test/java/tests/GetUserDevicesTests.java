package tests;

import api.GetUserDevicesAPI;
import api.GetVehicleSystemStatusAPI;
import base.BaseTest;
import config.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;
import utils.SchemaValidatorUtil;

public class GetUserDevicesTests extends BaseTest {
    @Test
    public void verifyGetUserDevicesContract() {

        String userID = ConfigManager.getProperty("username");

        Response response = RetryUtil.executeWithRetry(() ->
                GetUserDevicesAPI.getUserDevices(userID),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetUserDevicesAPISchema.json");
    }
}
