package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Plan {

    private String code;
    private String name;
    private String description;

}