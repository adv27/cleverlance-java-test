package com.cleverlance.airlabs.controller;

import com.cleverlance.airlabs.exception.PremiumAccountOnly;
import com.cleverlance.airlabs.model.CommonResponse;
import com.cleverlance.airlabs.model.airlabs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/airlabs")
public class AirlabsController {
    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    private String URLBuilder(String endpoint) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(endpoint)
                .queryParam("api_key", env.getProperty("airlabs.api-key"));
        return builder.toUriString();
    }

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airport> airports(@RequestParam(defaultValue = "") String name,
                                  @RequestParam(defaultValue = "") String code) {
        String endpoint = URLBuilder(env.getProperty("airlabs.airports"));
        CommonResponse<List<Airport>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Airport>>>() {
                }).getBody();
        List<Airport> airports = response.getResponse();
        // do filter by name & code (contains ignore case)
        if (!name.isEmpty()) {
            airports = airports
                    .stream()
                    .filter(a -> a.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (!code.isEmpty()) {
            airports = airports
                    .stream()
                    .filter(a -> a.getCode().toLowerCase().contains(code.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return airports;
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> cities() {
        String endpoint = URLBuilder(env.getProperty("airlabs.cities"));
        CommonResponse<List<City>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<City>>>() {
                }).getBody();
        List<City> cities = response.getResponse();
        return cities;
    }

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> countries() {
        String endpoint = URLBuilder(env.getProperty("airlabs.countries"));
        CommonResponse<List<Country>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Country>>>() {
                }).getBody();
        List<Country> countries = response.getResponse();
        return countries;
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airline> airlines() {
        String endpoint = URLBuilder(env.getProperty("airlabs.airlines"));
        CommonResponse<List<Airline>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Airline>>>() {
                }).getBody();
        List<Airline> airlines = response.getResponse();
        return airlines;
    }

    @GetMapping(value = "/taxes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tax> taxes() {
        throw new PremiumAccountOnly("For PREMIUM accounts only!");
    }

    @GetMapping(value = "/aircrafts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aircraft> aircrafts() {
        String endpoint = URLBuilder(env.getProperty("airlabs.aircrafts"));
        CommonResponse<List<Aircraft>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Aircraft>>>() {
                }).getBody();
        List<Aircraft> aircrafts = response.getResponse();
        return aircrafts;
    }

    @GetMapping(value = "/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airplane> airplanes() {
        throw new PremiumAccountOnly("For PREMIUM accounts only!");
    }


    @GetMapping(value = "/routes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Route> routes() {
        String endpoint = URLBuilder(env.getProperty("airlabs.routes"));
        CommonResponse<List<Route>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Route>>>() {
                }).getBody();
        List<Route> routes = response.getResponse();
        return routes;
    }

    @GetMapping(value = "/timezones", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Timezone> timezones() {
        String endpoint = URLBuilder(env.getProperty("airlabs.timezones"));
        CommonResponse<List<Timezone>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Timezone>>>() {
                }).getBody();
        List<Timezone> timezones = response.getResponse();
        return timezones;
    }

    @GetMapping(value = "/autocomplete", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airport> autoComplete(@RequestParam() String query) {
        String endpoint = URLBuilder(env.getProperty("airlabs.autocomplete"));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("query", query);
        endpoint = builder.toUriString();
        System.out.println(endpoint);
        CommonResponse<List<Airport>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Airport>>>() {
                }).getBody();
        List<Airport> airports = response.getResponse();
        return airports;
    }

    @GetMapping(value = "/nearby", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airport> nearby(@RequestParam() double lat,
                                @RequestParam() double lng,
                                @RequestParam() double distance) {
        String endpoint = URLBuilder(env.getProperty("airlabs.nearby"));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("lat", lat)
                .queryParam("lng", lng)
                .queryParam("distance", distance);
        endpoint = builder.toUriString();
        CommonResponse<List<Airport>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CommonResponse<List<Airport>>>() {
                }).getBody();
        List<Airport> airports = response.getResponse();
        return airports;
    }

    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flight> flights() {
        throw new PremiumAccountOnly("For PREMIUM accounts only!");
    }

    @GetMapping(value = "/timetable", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimeTable> timeTables() {
        throw new PremiumAccountOnly("For PREMIUM accounts only!");
    }
}