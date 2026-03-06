package tests;

import api.GetTripReportbyUsernameAPI;
import api.GetUserDetailsByNameAPI;
import base.BaseTest;
import config.ConfigManager;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GetTripReportbyUsernameTests extends BaseTest {
    @Feature("Trip Report by Username API")
    @Story("Get Trip Report by Username API")
    @Description("Verify trip reports are displayed based on username")
    @Test
    public void verifyTripReportbyUsername() {

        String username = ConfigManager.getProperty("username");
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("starttime",
                Long.parseLong(ConfigManager.getProperty("starttime")));

        requestBody.put("endtime",
                Long.parseLong(ConfigManager.getProperty("endtime")));

        requestBody.put("page",
                Integer.parseInt(ConfigManager.getProperty("page")));

        requestBody.put("size",
                Integer.parseInt(ConfigManager.getProperty("size")));

        String[] pdidsArray =
                ConfigManager.getProperty("pdids").split(",");

        requestBody.put("pdids",
                Arrays.asList(pdidsArray));

        Response response = RetryUtil.executeWithRetry(() ->
                GetTripReportbyUsernameAPI.postTripReportbyUsername(username, requestBody),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/GetTripReportbyUsernameAPISchema.json");
    }
}

/*Implemented a reusable ResponseValidator utility to centralize common API validations like status code, SLA response time,
headers, and content-type.
SLA thresholds are environment-configurable, ensuring flexibility across SIT, QA, and PROD.
 */
