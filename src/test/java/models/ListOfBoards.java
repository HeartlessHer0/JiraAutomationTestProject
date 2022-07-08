package models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

@Data
public class ListOfBoards {
    @ToString.Exclude
    public final int MAX_RESULTS = 50;
    @ToString.Exclude
    public final long START_AT = 0;

    private int maxResults;
    private long startAt;
    private int total;
    @JsonUnwrapped
    private boolean isLast;
    private Board[] values;
}