package config;

public class Endpoints {
    public static final String GET_ACCESS_TOKEN = "/oauth/token";
    public static final String GET_USER_DETAILS_BY_NAME = "/users/name/{username_jhs}";
    public static final String GET_VEHICLE_SYSTEM_STATUS = "/vehiclesystemstatus/devices/{pdid}";
    public static final String GET_USER_DEVICES="/devices/users/{username_jhs}";
    public static final String GET_USER_DEVICES_BY_PDID="/devices/users/{username_jhs}/devices/{pdid}";
    public static final String GET_TRIP_REPORT_BY_USERNAME="/tripanalysis/userDevices/{username_jhs}";
    public static final String POST_CONFIGURE_MQTT_TRIGGERS="/triggers/devices/{pdid}";
    public static final String POST_WHITELIST_AND_ADD_CAR="/enterprise/site-admin/connection";
    public static final String HARD_DELETE_DEVICE="/connections/deleteDevice/{imei}";
}

//Reusable Endpoints