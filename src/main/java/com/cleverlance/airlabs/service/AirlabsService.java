package com.cleverlance.airlabs.service;

import com.cleverlance.airlabs.entity.airlabs.*;

import java.util.List;

public interface AirlabsService {
    public abstract List<Airport> airports(String name, String code);

    public abstract List<City> cities();

    public abstract List<Country> countries();

    public abstract List<Airline> airlines();

    public abstract List<Tax> taxes();

    public abstract List<Aircraft> aircrafts();

    public abstract List<Airplane> airplanes();

    public abstract List<Route> routes();

    public abstract List<Timezone> timezones();

    public abstract AutoComplete autoComplete(String query);

    public abstract List<Airport> nearby(double lat, double lng, double distance);

    public abstract List<Flight> flights();

    public abstract List<TimeTable> timeTables();
}
