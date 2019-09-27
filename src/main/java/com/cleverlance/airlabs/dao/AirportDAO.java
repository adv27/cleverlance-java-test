package com.cleverlance.airlabs.dao;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDAO extends CrudRepository<Airport, String> {
    public Airport findByCode(String code);
}
