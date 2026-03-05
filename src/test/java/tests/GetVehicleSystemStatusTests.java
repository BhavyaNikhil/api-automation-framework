package tests;

import api.GetUserDetailsByNameAPI;
import api.GetVehicleSystemStatusAPI;
import base.BaseTest;
import config.ConfigManager;
import config.Endpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.AllureUtil;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;
import utils.SchemaValidatorUtil;

public class GetVehicleSystemStatusTests extends BaseTest {
    @Test
    public void verifyVehicleSystemStatusContract() {

        String pdid = ConfigManager.getProperty("pdid");

        Response response = RetryUtil.executeWithRetry(() ->
                GetVehicleSystemStatusAPI.getVehicleSystemStatus(pdid),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetVehicleSystemStatusAPISchema.json");
    }
}
