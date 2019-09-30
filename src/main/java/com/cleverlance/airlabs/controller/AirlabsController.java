package com.cleverlance.airlabs.controller;

import com.cleverlance.airlabs.entity.Response;
import com.cleverlance.airlabs.entity.airlabs.*;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.AirlabsService;
import com.cleverlance.airlabs.service.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/airlabs")
public class AirlabsController {
    private Logger logger = LoggerFactory.getLogger(AirlabsController.class);

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirlabsService airlabsService;

    @Autowired
    private PingService pingService;

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public void ping() {
        pingService.printAllAirports(System.out);
    }

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> airports(@RequestParam(defaultValue = "", required = false) String code) {
        Response response;
        code = code.toUpperCase().trim();
        if (!code.isEmpty()) {
            Airport airport = airlabsService.getAirportByCode(code);
            response = new Response<>(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    airport);
        } else {
            List<Airport> airports = airlabsService.getAirports();
            response = new Response<>(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    airports);

            // store Airports data
            List<Airport> insert = airports.stream()
                    .filter(airport -> !airportRepository.existsById(airport.getCode()))
                    .collect(Collectors.toList());
            // bulk insert
            airportRepository.saveAll(insert);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<City>>> cities() {
        List<City> cities = airlabsService.getCities();
        Response<List<City>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                cities);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Country>>> countries() {
        List<Country> countries = airlabsService.getCountries();
        Response<List<Country>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                countries);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Airline>>> airlines() {
        List<Airline> airlines = airlabsService.getAirlines();
        Response<List<Airline>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                airlines);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/taxes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Tax>>> taxes() {
        List<Tax> taxes = airlabsService.getTaxes();
        Response<List<Tax>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                taxes);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/aircrafts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Aircraft>>> aircrafts() {
        List<Aircraft> aircrafts = airlabsService.getAircrafts();
        Response<List<Aircraft>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                aircrafts);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Airplane>>> airplanes() {
        List<Airplane> airplanes = airlabsService.getAirplanes();
        Response<List<Airplane>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                airplanes);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/routes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Route>>> routes() {
        List<Route> routes = airlabsService.getRoutes();
        Response<List<Route>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                routes);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/timezones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Timezone>>> timezones() {
        List<Timezone> timezones = airlabsService.getTimezones();
        Response<List<Timezone>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                timezones);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/autocomplete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response<AutoComplete>> autoComplete(@RequestParam() String query) {
        AutoComplete autoComplete = airlabsService.getAutoComplete(query);
        Response<AutoComplete> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                autoComplete);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/nearby", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Airport>>> nearby(
            @RequestParam() double lat,
            @RequestParam() double lng,
            @RequestParam() double distance) {
        List<Airport> airports = airlabsService.getNearby(lat, lng, distance);
        Response<List<Airport>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                airports);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response<List<Flight>>> flights() {
        List<Flight> flights = airlabsService.getFlights();
        Response<List<Flight>> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                flights);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/timetable", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response<Timetable>> timeTables() {
        Timetable timetables = airlabsService.getTimetable();
        Response<Timetable> response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                timetables);
        return ResponseEntity.ok(response);
    }
}