package models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Board {
    @EqualsAndHashCode.Exclude
    private transient long id;
    @EqualsAndHashCode.Exclude
    private String self;
    private String name;
    private String type;
    @EqualsAndHashCode.Exclude
    private BoardLocation location;
    @JsonUnwrapped
    @EqualsAndHashCode.Exclude
    private long filterId; //todo del?
}
