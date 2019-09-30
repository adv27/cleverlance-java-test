package com.cleverlance.airlabs.service;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlabsConsumerService {
    /**
     * receive data from all airports
     *
     * @return data of all airports
     */
    List<Airport> getAirports();

    /**
     * Receive data from a specific airport
     *
     * @param code airport code
     * @return data of specific airport, return null when no airport found
     */
    Airport getAirportByCode(String code);
}
