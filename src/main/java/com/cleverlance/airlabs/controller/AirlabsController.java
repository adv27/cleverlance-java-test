package com.cleverlance.airlabs.controller;

import com.cleverlance.airlabs.entity.Response;
import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.AirlabsConsumerService;
import com.cleverlance.airlabs.service.PingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Airlabs")
public class AirlabsController {
    private Logger logger = LoggerFactory.getLogger(AirlabsController.class);

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirlabsConsumerService airlabsConsumerService;

    @Autowired
    private PingService pingService;

    @ApiOperation(value = "Read contents of Airports table in H2 repository and print the data to System.out")
    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public void ping() {
        pingService.printAllAirports(System.out);
    }

    /**
     * Receive data of all Airports and store the Airports data to H2 repository
     *
     * @return data of all Airports
     */
    @ApiOperation(value = "View list data of all Airports or data of specific Airport by its code")
    @GetMapping(value = "/airports/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> airports() {
        Response response;
        List<Airport> airports = airlabsConsumerService.getAirports();
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

        return ResponseEntity.ok(response);
    }

    /**
     * Get specific Airport by its code
     *
     * @param code Airport code
     * @return data of specific Airport when @param code is not empty
     */
    @ApiOperation(value = "View list data of all Airports or data of specific Airport by its code")
    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> airportByCode(@RequestParam(defaultValue = "", required = false) String code) {
        code = code.toUpperCase().trim();
        if (code.isEmpty()) {
            return airports();
        }
        Airport airport = airlabsConsumerService.getAirportByCode(code);
        Response response = new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                airport);
        return ResponseEntity.ok(response);
    }

}