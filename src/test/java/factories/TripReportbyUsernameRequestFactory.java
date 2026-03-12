package factories;

import config.ConfigManager;
import models.*;

import java.util.Arrays;

public class TripReportbyUsernameRequestFactory {
    public static TripReportbyUsernameRequest defaultRequest() {
        return TripReportbyUsernameRequest.builder()
                .starttime(Long.parseLong(ConfigManager.getProperty("starttime")))
                .endtime(Long.parseLong(ConfigManager.getProperty("endtime")))
                .page(Integer.parseInt(ConfigManager.getProperty("page")))
                .size(Integer.parseInt(ConfigManager.getProperty("size")))
                .skiproutedata(Boolean.parseBoolean(ConfigManager.getProperty("skiproutedata")))
                .pdids(Arrays.asList(ConfigManager.getProperty("pdids").split(",")))
                .build();
    }
}