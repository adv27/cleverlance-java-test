package com.cleverlance.airlabs;

import com.cleverlance.airlabs.entity.AirlabsResponse;
import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.service.AirlabsConsumerService;
import com.cleverlance.airlabs.util.RestTemplateResponseErrorHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@RestClientTest
public class AirlabsConsumerServiceTests {
    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private RestTemplateBuilder builder;

    @Autowired
    private Environment env;

    @MockBean
    private AirlabsConsumerService airlabsConsumerService;

    @Test(expected = ResponseStatusException.class)
    public void givenRemoteApiCall_when404Error_thenThrowNotFound() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.server);

        RestTemplate restTemplate = this.builder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

        final String API_KEY = env.getProperty("airlabs.api-key");
        String endpoint = env.getProperty("airlabs.airports");
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(endpoint)
                .queryParam("api_key", API_KEY);
        endpoint = builder.toUriString();

        this.server
                .expect(ExpectedCount.once(), requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        when(restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AirlabsResponse<List<Airport>>>() {
                }).getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
        this.server.verify();
    }
}
