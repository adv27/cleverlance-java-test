package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Timezone {
    private String timezone;
    @JsonProperty("country_code")
    private String countryCode;
    private String gtm;
    private String dst;

    public Timezone() {
    }

    public Timezone(String timezone, String countryCode, String gtm, String dst) {
        this.timezone = timezone;
        this.countryCode = countryCode;
        this.gtm = gtm;
        this.dst = dst;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getGtm() {
        return gtm;
    }

    public void setGtm(String gtm) {
        this.gtm = gtm;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
