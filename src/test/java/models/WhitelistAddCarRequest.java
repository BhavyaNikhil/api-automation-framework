package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WhitelistAddCarRequest {
    private String adminEmail;
    private String bpid;
    private String category;
    private Device device;
    private Plan plan;
    private Vehicle vehicle;
    private String serviceStatus;
    private String vehicleType;
}