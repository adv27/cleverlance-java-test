package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String code;
    private String name;
    @JsonProperty("country_code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Nullable
    private String countryCode;
    @JsonProperty("country_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Nullable
    private String countryName;

    public City(String code, String name, @Nullable String countryName) {
        this.code = code;
        this.name = name;
        this.countryName = countryName;
    }
}
