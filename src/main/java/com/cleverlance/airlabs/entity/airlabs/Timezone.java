package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timezone {
    private String timezone;
    @JsonProperty("country_code")
    private String countryCode;
    private int gtm;
    private int dst;
}
