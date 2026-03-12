package tests;

import api.GetVehicleSystemStatusAPI;
import api.HardDeleteDeviceAPI;
import base.BaseTest;
import config.ConfigManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;

public class HardDeleteDeviceTests extends BaseTest{
    @Feature("Hard Delete Device API")
    @Story("Hard Delete Device by IMEI API")
    @Description("Deletes the device from backend")
    @Test
    public void hardDeleteDeviceContract(){
        String imei = ConfigManager.getProperty("imei");
        Response response = RetryUtil.executeWithRetry(() ->
                HardDeleteDeviceAPI.hardDeleteDevice(imei),3);
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/HardDeleteDeviceAPISchema.json");
    }
}