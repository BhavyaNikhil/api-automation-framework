package tests;

import api.GetTripReportbyUsernameAPI;
import api.GetUserDetailsByNameAPI;
import base.BaseTest;
import config.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;
import utils.SchemaValidatorUtil;

public class GetUserDetailsByNameTests extends BaseTest {
    @Test
    public void verifyUserDetailsContract() {

        String username = ConfigManager.getProperty("username");

        Response response = RetryUtil.executeWithRetry(() ->
                GetUserDetailsByNameAPI.getUserByName(username),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetUserDetailsByNameAPISchema.json");

    }
}

//Run this class