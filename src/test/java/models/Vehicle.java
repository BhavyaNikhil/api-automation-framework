package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {

    private String make;
    private String model;
    private String year;
    private String registrationNumber;
    private String fuelType;
}