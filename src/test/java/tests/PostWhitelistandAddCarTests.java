package tests;

import api.GetTripReportbyUsernameAPI;
import api.PostWhitelistandAddCarAPI;
import base.BaseTest;
import config.ConfigManager;
import factories.WhitelistAddCarRequestFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.WhitelistAddCarRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PostWhitelistandAddCarTests extends BaseTest {
    @Feature("Whitelist and Add Car API")
    @Story("Post Whitelist and Add Car API")
    @Description("Verify whitelist and add car")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyWhitelistandAddCar() {
        WhitelistAddCarRequest requestBody = WhitelistAddCarRequestFactory.defaultRequest();
        Response response = RetryUtil.executeWithRetry(() ->
                PostWhitelistandAddCarAPI.postWhitelistandAddCar(requestBody),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/PostWhitelistandAddCarAPISchema.json");
    }
}
