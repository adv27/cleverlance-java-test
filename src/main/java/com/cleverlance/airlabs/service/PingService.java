package com.cleverlance.airlabs.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public interface PingService {
    /**
     * Print all Airports data to output stream
     *
     * @param stream the stream use to print out all Airports
     */
    void printAllAirports(PrintStream stream);
}
