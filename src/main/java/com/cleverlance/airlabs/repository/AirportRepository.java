package com.cleverlance.airlabs.repository;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    Airport findByCode(String code);
}
