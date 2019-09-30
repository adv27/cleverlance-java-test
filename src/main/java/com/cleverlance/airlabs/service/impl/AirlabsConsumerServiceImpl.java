package com.cleverlance.airlabs.service.impl;

import com.cleverlance.airlabs.entity.AirlabsResponse;
import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.service.AirlabsConsumerService;
import com.cleverlance.airlabs.util.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AirlabsConsumerServiceImpl implements AirlabsConsumerService {
    @Autowired
    private Environment env;

    private RestTemplate restTemplate;

    @Autowired
    public AirlabsConsumerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public AirlabsConsumerServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Airport> getAirports() {
        final String API_KEY = env.getProperty("airlabs.api-key");
        String endpoint = env.getProperty("airlabs.airports");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(endpoint)
                .queryParam("api_key", API_KEY);
        endpoint = builder.toUriString();
        AirlabsResponse<List<Airport>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AirlabsResponse<List<Airport>>>() {
                }).getBody();
        List<Airport> airports = response.getResponse();
        return airports;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Airport getAirportByCode(String code) {
        final String API_KEY = env.getProperty("airlabs.api-key");
        String endpoint = env.getProperty("airlabs.airports");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(endpoint)
                .queryParam("api_key", API_KEY)
                .queryParam("code", code);
        endpoint = builder.toUriString();
        AirlabsResponse<List<Airport>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AirlabsResponse<List<Airport>>>() {
                }).getBody();
        List<Airport> airports = response.getResponse();
        if (!CollectionUtils.isEmpty(airports)) {
            return airports.get(0);
        }
        return null;
    }
}
