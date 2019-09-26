package com.cleverlance.airlabs.model.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    private String code;
    private String code3;
    @JsonProperty("iso_numeric")
    private String isoNumeric;
    private String name;

    public Country() {
    }

    public Country(String code, String code3, String isoNumeric, String name) {
        this.code = code;
        this.code3 = code3;
        this.isoNumeric = isoNumeric;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
