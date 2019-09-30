package com.cleverlance.airlabs.service;

import com.cleverlance.airlabs.entity.airlabs.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlabsService {
    List<Airport> getAirports();

    Airport getAirportByCode(String code);

    List<City> getCities();

    List<Country> getCountries();

    List<Airline> getAirlines();

    List<Tax> getTaxes();

    List<Aircraft> getAircrafts();

    List<Airplane> getAirplanes();

    List<Route> getRoutes();

    List<Timezone> getTimezones();

    AutoComplete getAutoComplete(String query);

    List<Airport> getNearby(double lat, double lng, double distance);

    List<Flight> getFlights();

    Timetable getTimetable();
}
