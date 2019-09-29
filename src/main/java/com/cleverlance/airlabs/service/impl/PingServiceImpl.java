package com.cleverlance.airlabs.service.impl;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.List;

@Service
public class PingServiceImpl implements PingService {
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public void printAllAirports(PrintStream stream) {
        List<Airport> airports = airportRepository.findAll();
        String pingMessage = airports.toString();
        stream.println(pingMessage);
    }
}
