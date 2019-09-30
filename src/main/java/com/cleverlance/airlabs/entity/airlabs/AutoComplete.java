package com.cleverlance.airlabs.entity.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
