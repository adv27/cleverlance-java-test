package com.cleverlance.airlabs.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public interface PingService {
    void printAllAirports(PrintStream stream);
}
