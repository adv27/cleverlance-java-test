package com.cleverlance.airlabs.service;

import com.cleverlance.airlabs.entity.airlabs.*;

import java.util.List;

public interface AirlabsService {
    public abstract List<Airport> getAirports();

    public abstract Airport getAirportByCode(String code);

    public abstract List<City> getCities();

    public abstract List<Country> getCountries();

    public abstract List<Airline> getAirlines();

    public abstract List<Tax> getTaxes();

    public abstract List<Aircraft> getAircrafts();

    public abstract List<Airplane> getAirplanes();

    public abstract List<Route> getRoutes();

    public abstract List<Timezone> getTimezones();

    public abstract AutoComplete getAutoComplete(String query);

    public abstract List<Airport> getNearby(double lat, double lng, double distance);

    public abstract List<Flight> getFlights();

    public abstract List<Timetable> getTimetables();
}
