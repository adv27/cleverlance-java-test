package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AutoComplete {
    private List<Country> countries;
    private List<City> cities;
    private List<Airport> airports;
    @JsonProperty("cities_by_countries")
    private List<City> citiesByCountries;
    @JsonProperty("cities_by_airports")
    private List<City> citiesByAirports;
    @JsonProperty("airports_by_countries")
    private List<Airport> airportsByCountries;
    @JsonProperty("airports_by_cities")
    private List<Airport> airportsByCities;

    public AutoComplete() {
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<City> getCitiesByCountries() {
        return citiesByCountries;
    }

    public void setCitiesByCountries(List<City> citiesByCountries) {
        this.citiesByCountries = citiesByCountries;
    }

    public List<City> getCitiesByAirports() {
        return citiesByAirports;
    }

    public void setCitiesByAirports(List<City> citiesByAirports) {
        this.citiesByAirports = citiesByAirports;
    }

    public List<Airport> getAirportsByCountries() {
        return airportsByCountries;
    }

    public void setAirportsByCountries(List<Airport> airportsByCountries) {
        this.airportsByCountries = airportsByCountries;
    }

    public List<Airport> getAirportsByCities() {
        return airportsByCities;
    }

    public void setAirportsByCities(List<Airport> airportsByCities) {
        this.airportsByCities = airportsByCities;
    }
}
