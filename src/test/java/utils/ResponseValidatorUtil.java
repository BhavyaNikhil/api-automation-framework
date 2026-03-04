package utils;

import config.ConfigManager;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class ResponseValidatorUtil {
    public static void validateStandardResponse(Response response, int expectedStatusCode, String schemaPath){
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200,"Status code mismatch");

        long maxTime = Long.parseLong(ConfigManager.getProperty("maxResponseTime")); //Response Time Validation (Soft way) - SLA
        long actualTime = response.getTime();
        System.out.println("Response Time: " + actualTime + " ms");
        softAssert.assertTrue(actualTime < maxTime,"Response time exceeded. Actual: " + actualTime + "ms");

        //Content-Type Validation
        softAssert.assertTrue(response.getContentType().contains("application/json"),"Invalid Content-Type");

        // -------- PRINT RESPONSE IF FAILURE --------
        if (response.getStatusCode() != expectedStatusCode) {
            System.out.println("Response Body on Failure:");
            System.out.println(response.getBody().asPrettyString());
        }

        // -------- SCHEMA VALIDATION --------
        if (schemaPath != null && !schemaPath.isEmpty()) {
            SchemaValidatorUtil.validateSchema(response, schemaPath);
        }

        softAssert.assertAll();
    }
}
