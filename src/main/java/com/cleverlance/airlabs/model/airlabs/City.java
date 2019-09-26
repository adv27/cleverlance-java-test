package com.cleverlance.airlabs.model.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    private String code;
    private String name;
    @JsonProperty("country_code")
    private String countryCode;

    public City() {
    }

    public City(String code, String name, String countryCode) {
        this.code = code;
        this.name = name;
        this.countryCode = countryCode;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
