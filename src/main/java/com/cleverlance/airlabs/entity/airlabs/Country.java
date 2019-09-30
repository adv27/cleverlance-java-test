package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private String code;
    private String code3;
    @JsonProperty("iso_numeric")
    private int isoNumeric;
    private String name;
}
