package com.cleverlance.airlabs.service.impl;

import com.cleverlance.airlabs.entity.CommonResponse;
import com.cleverlance.airlabs.entity.airlabs.*;
import com.cleverlance.airlabs.exception.ApiKeyNotFoundException;
import com.cleverlance.airlabs.exception.PremiumAccountOnlyException;
import com.cleverlance.airlabs.service.AirlabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CleverlanceAirlabsService implements AirlabsService {
    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;


    private String URLBuilder(String endpoint) {
        final String API_KEY = env.getProperty("airlabs.api-key");
        if (API_KEY != null && !API_KEY.isEmpty()) {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(endpoint)
                    .queryParam("api_key", API_KEY);
            return builder.toUriString();
        }
        throw new ApiKeyNotFoundException("Please provide API_KEY");
    }

    @Override
    public List<Airport> airports(String name, String code) {
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Tax> taxes() {
        throw new PremiumAccountOnlyException("For PREMIUM accounts only!");
    }

    @Override
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

    @Override
    public List<Airplane> airplanes() {
        throw new PremiumAccountOnlyException("For PREMIUM accounts only!");
    }

    @Override
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

    @Override
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

    @Override
    public List<Airport> autoComplete(String query) {
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

    @Override
    public List<Airport> nearby(double lat, double lng, double distance) {
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

    @Override
    public List<Flight> flights() {
        throw new PremiumAccountOnlyException("For PREMIUM accounts only!");
    }

    @Override
    public List<TimeTable> timeTables() {
        throw new PremiumAccountOnlyException("For PREMIUM accounts only!");
    }
}
