package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @JsonProperty("flight_number")
    private String flightNumber;
    @JsonProperty("airline_iata")
    private String airlineIATA;
}
