package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TripReportbyUsernameRequest {
    private long starttime;
    private long endtime;
    private int page;
    private int size;
    private boolean skiproutedata;
    private List<String> pdids;
}
