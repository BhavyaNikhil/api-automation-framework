package factories;

import config.ConfigManager;
import models.Device;
import models.Plan;
import models.Vehicle;
import models.WhitelistAddCarRequest;

public class WhitelistAddCarRequestFactory {

    public static WhitelistAddCarRequest defaultRequest() {

        Device device = Device.builder()
                .imei(ConfigManager.getProperty("imei"))
                .imsi(ConfigManager.getProperty("imei"))
                .msisdn(ConfigManager.getProperty("imei"))
                .iccid(ConfigManager.getProperty("imei") + "10000")
                .build();

        Plan plan = Plan.builder()
                .code("499")
                .name("Jio")
                .description("Jio Data Call")
                .build();

        Vehicle vehicle = Vehicle.builder()
                .make(ConfigManager.getProperty("make"))
                .model(ConfigManager.getProperty("model"))
                .year(ConfigManager.getProperty("year"))
                .registrationNumber(ConfigManager.getProperty("registrationNumber"))
                .fuelType(ConfigManager.getProperty("fuelType"))
                .build();

        return WhitelistAddCarRequest.builder()
                .adminEmail(ConfigManager.getProperty("username_jhs"))
                .bpid(ConfigManager.getProperty("imei"))
                .category("EnterpriseConnectedVehicles")
                .device(device)
                .plan(plan)
                .vehicle(vehicle)
                .serviceStatus("active")
                .vehicleType("FOUR_WHEELER_HEV")
                .build();
    }
}