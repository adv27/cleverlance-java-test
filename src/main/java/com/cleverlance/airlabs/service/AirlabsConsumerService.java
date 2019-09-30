package com.cleverlance.airlabs.service;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlabsConsumerService {
    List<Airport> getAirports();

    Airport getAirportByCode(String code);
}
