package tests;

import api.GetTripReportbyUsernameAPI;
import api.GetUserDetailsByNameAPI;
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

public class GetUserDetailsByNameTests extends BaseTest {
    @Feature("User Details by Name API")
    @Story("Get User Details by Username API")
    @Description("Verify user details are displayed based on username")
    @Test
    public void verifyUserDetailsContract() {

        String username = ConfigManager.getProperty("username_jhs");

        Response response = RetryUtil.executeWithRetry(() ->
                GetUserDetailsByNameAPI.getUserByName(username),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetUserDetailsByNameAPISchema.json");

    }
}

//Run this class