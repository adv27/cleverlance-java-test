package com.cleverlance.airlabs.controller;

import com.cleverlance.airlabs.entity.Response;
import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.AirlabsConsumerService;
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
    private AirlabsConsumerService airlabsConsumerService;

    @Autowired
    private PingService pingService;

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public void ping() {
        pingService.printAllAirports(System.out);
    }

    /**
     * Receive data of airports
     *
     * @param code airport code
     * @return data of airports or data of specific airport when @param code is not empty
     */
    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> airports(@RequestParam(defaultValue = "", required = false) String code) {
        Response response;
        code = code.toUpperCase().trim();
        if (!code.isEmpty()) {
            Airport airport = airlabsConsumerService.getAirportByCode(code);
            response = new Response<>(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    airport);
        } else {
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
        }
        return ResponseEntity.ok(response);
    }
}