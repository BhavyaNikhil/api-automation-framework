package tests;

import api.GetTripReportbyUsernameAPI;
import base.BaseTest;
import config.ConfigManager;
import factories.TripReportbyUsernameRequestFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.TripReportbyUsernameRequest;
import org.testng.annotations.Test;
import utils.*;

public class GetTripReportbyUsernameTests extends BaseTest {
    @Feature("Trip Report by Username API")
    @Story("Get Trip Report by Username API")
    @Description("Verify trip reports are displayed based on username")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyTripReportbyUsername() {

        String username = ConfigManager.getProperty("username_jhs");
        TripReportbyUsernameRequest requestBody = TripReportbyUsernameRequestFactory.defaultRequest();

        Response response = RetryUtil.executeWithRetry(() ->
                GetTripReportbyUsernameAPI.postTripReportbyUsername(username, requestBody),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetTripReportbyUsernameAPISchema.json");
    }
}

/*Implemented a reusable ResponseValidator utility to centralize common API validations like status code, SLA response time,
headers, and content-type.
SLA thresholds are environment-configurable, ensuring flexibility across SIT, QA, and PROD.
Implemented a Builder pattern for request payload creation to improve reusability and maintainability.
This avoids hardcoding request data inside test classes and allows scalable test data management.
 */
