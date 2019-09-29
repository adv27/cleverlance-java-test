package com.cleverlance.airlabs.service.impl;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PingServiceImpl implements PingService {
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public String printAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        String pingMessage = airports.toString();
        System.out.println(pingMessage);
        return pingMessage;
    }
}
