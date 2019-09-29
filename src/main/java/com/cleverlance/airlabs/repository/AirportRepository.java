package com.cleverlance.airlabs.repository;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {
    Airport findByCode(String code);
}
