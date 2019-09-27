package com.cleverlance.airlabs.controller;

import com.cleverlance.airlabs.dao.AirportDAO;
import com.cleverlance.airlabs.entity.airlabs.*;
import com.cleverlance.airlabs.service.AirlabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/airlabs")
public class AirlabsController {

    @Autowired
    private AirportDAO airportDAO;

    @Autowired
    private AirlabsService airlabsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/ping")
    public void ping() {
        System.out.println(airportDAO.findAll());
    }


    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object airports(@RequestParam(defaultValue = "", required = false) String code) {
        if (code != null && !code.isEmpty()) {
            Airport airport = airlabsService.getAirportByCode(code);
            return airport;
        }

        List<Airport> airports = airlabsService.getAirports();

        // store Airports data
        List<Airport> insert = airports.stream()
                .filter(airport -> !airportDAO.existsById(airport.getCode()))
                .collect(Collectors.toList());
        // bulk insert
        airportDAO.saveAll(insert);

        return airports;
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> cities() {
        List<City> cities = airlabsService.getCities();
        return cities;
    }

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> countries() {
        List<Country> countries = airlabsService.getCountries();
        return countries;
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airline> airlines() {
        List<Airline> airlines = airlabsService.getAirlines();
        return airlines;
    }

    @GetMapping(value = "/taxes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tax> taxes() {
        List<Tax> taxes = airlabsService.getTaxes();
        return taxes;
    }

    @GetMapping(value = "/aircrafts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aircraft> aircrafts() {
        List<Aircraft> aircrafts = airlabsService.getAircrafts();
        return aircrafts;
    }

    @GetMapping(value = "/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airplane> airplanes() {
        List<Airplane> airplanes = airlabsService.getAirplanes();
        return airplanes;
    }


    @GetMapping(value = "/routes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Route> routes() {
        List<Route> routes = airlabsService.getRoutes();
        return routes;
    }

    @GetMapping(value = "/timezones", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Timezone> timezones() {
        List<Timezone> timezones = airlabsService.getTimezones();
        return timezones;
    }

    @GetMapping(value = "/autocomplete", produces = MediaType.APPLICATION_JSON_VALUE)
    public AutoComplete autoComplete(@RequestParam() String query) {
        AutoComplete autoComplete = airlabsService.getAutoComplete(query);
        return autoComplete;
    }

    @GetMapping(value = "/nearby", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Airport> nearby(@RequestParam() double lat,
                                @RequestParam() double lng,
                                @RequestParam() double distance) {
        List<Airport> airports = airlabsService.getNearby(lat, lng, distance);
        return airports;
    }

    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flight> flights() {
        List<Flight> flights = airlabsService.getFlights();
        return flights;
    }

    @GetMapping(value = "/timetable", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Timetable> timeTables() {
        List<Timetable> timetables = airlabsService.getTimetables();
        return timetables;
    }
}