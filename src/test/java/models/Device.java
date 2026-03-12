package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Device {

    private String imei;
    private String imsi;
    private String msisdn;
    private String iccid;

}

/*
@Data automatically creates
getters
setters
toString
equals
hashCode
 */