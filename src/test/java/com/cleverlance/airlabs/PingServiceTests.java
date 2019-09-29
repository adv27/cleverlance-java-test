package com.cleverlance.airlabs;

import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.PingService;
import com.cleverlance.airlabs.service.impl.PingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PingServiceTests {
    @TestConfiguration
    public static class PingServiceTestsConfiguration {
        @Bean
        PingService pingService() {
            return new PingServiceImpl();
        }
    }

    @MockBean
    private AirportRepository airportRepository;

    @Autowired
    private PingService pingService;

    @Test
    public void givenAirports_whenPing_thenPrintAllAirports() throws Exception {
        // given
        Airport hn = new Airport("HN", "Ha Noi");
        Airport hcm = new Airport("HCM", "Ho Chi Minh");
        List<Airport> airports = Arrays.asList(hn, hcm);
        Mockito.when(airportRepository.findAll())
                .thenReturn(airports);

        // when
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        pingService.printAllAirports(stream);

        // then
        assertThat(outputStream.toString())
                .isEqualTo(airports.toString() + '\n');
    }
}