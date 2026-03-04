package tests;

import api.GetTripReportbyUsernameAPI;
import api.PostWhitelistandAddCarAPI;
import base.BaseTest;
import config.ConfigManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ResponseValidatorUtil;
import utils.RetryUtil;
import utils.SchemaValidatorUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PostWhitelistandAddCarTests extends BaseTest {
    @Test
    public void verifyWhitelistandAddCar() {
        Map<String, Object> device = new HashMap<>();
        device.put("imei",ConfigManager.getProperty("imei"));
        device.put("imsi",ConfigManager.getProperty("imei"));
        device.put("msisdn",ConfigManager.getProperty("imei"));
        device.put("iccid",ConfigManager.getProperty("imei")+Integer.parseInt("10000"));

        Map<String, Object> plan = new HashMap<>();
        plan.put("code","499");
        plan.put("name","Jio");
        plan.put("description","Jio Data Call");

        Map<String, Object> vehicle = new HashMap<>();
        vehicle.put("make",ConfigManager.getProperty("make"));
        vehicle.put("model",ConfigManager.getProperty("model"));
        vehicle.put("year",ConfigManager.getProperty("year"));
        vehicle.put("registrationNumber",ConfigManager.getProperty("registrationNumber"));
        vehicle.put("fuelType",ConfigManager.getProperty("fuelType"));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("adminEmail",
                ConfigManager.getProperty("username"));
        requestBody.put("bpid",
                ConfigManager.getProperty("imei"));
        requestBody.put("category", "EnterpriseConnectedVehicles");
        requestBody.put("device",device);
        requestBody.put("plan",plan);
        requestBody.put("vehicle",vehicle);
        requestBody.put("serviceStatus","active");
        requestBody.put("vehicleType","FOUR_WHEELER_HEV");

        Response response = RetryUtil.executeWithRetry(() ->
                PostWhitelistandAddCarAPI.postWhitelistandAddCar(requestBody),3);

        //Standard and Schema Validations
        ResponseValidatorUtil.validateStandardResponse(response,200,"schemas/PostWhitelistandAddCarAPISchema.json");
    }
}
