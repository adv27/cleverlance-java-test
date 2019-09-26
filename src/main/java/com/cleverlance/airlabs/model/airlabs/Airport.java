package com.cleverlance.airlabs.model.airlabs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Airport {
    private String code;
    private String name;
    @JsonProperty("country_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String countryName;

    public Airport() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
