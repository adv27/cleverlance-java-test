package com.cleverlance.airlabs;

import com.cleverlance.airlabs.controller.AirlabsController;
import com.cleverlance.airlabs.entity.airlabs.*;
import com.cleverlance.airlabs.exception.PremiumAccountOnlyException;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.AirlabsService;
import com.cleverlance.airlabs.service.PingService;
import com.cleverlance.airlabs.service.impl.CleverlanceAirlabsService;
import com.cleverlance.airlabs.service.impl.PingServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AirlabsController.class)
public class AirlabsControllerTests {
    @TestConfiguration
    public static class PingServiceTestsConfiguration {
        @Bean
        PingService pingService() {
            return new PingServiceImpl();
        }

        @Bean
        AirlabsService airlabsService() {
            return new CleverlanceAirlabsService();
        }

    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirportRepository airportRepository;

    @MockBean
    private AirlabsService airlabsService;

    @MockBean
    private PingService pingService;

    @Test
    public void testPing() throws Exception {
        mvc.perform(get("/airlabs/ping"))
                .andExpect((status().isOk()));
    }

    @Test
    public void givenAirports_whenGetAirports_thenReturnJsonArray() throws Exception {
        Airport anaa = new Airport("AAA", "Anaa");
        Airport arrabury = new Airport("AAB", "Arrabury");
        List<Airport> allAirports = Arrays.asList(anaa, arrabury);

        given(airlabsService.getAirports()).willReturn(allAirports);

        mvc.perform(get("/airlabs/airports")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.data[0].code").value(anaa.getCode()))
                .andExpect(jsonPath("$.data[0].name").value(anaa.getName()))
                .andExpect(jsonPath("$.data[1].code").value(arrabury.getCode()))
                .andExpect(jsonPath("$.data[1].name").value(arrabury.getName()));
    }

    @Test
    public void givenAirports_whenGetAirportByCode_thenReturnJsonObject() throws Exception {
        Airport arrabury = new Airport("AAB", "Arrabury");

        final String codeQuery = "AAB";

        given(airlabsService.getAirportByCode(codeQuery)).willReturn(arrabury);

        mvc.perform(get("/airlabs/airports")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("code", codeQuery))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.code").value(arrabury.getCode()))
                .andExpect(jsonPath("$.data.name").value(arrabury.getName()));
    }

    @Test
    public void givenAirport_whenGetAirports_thenReturnJsonArray() throws Exception {
        Airport anaa = new Airport("AAM", "Mala Mala");
        List<Airport> allAirports = Arrays.asList(anaa);

        given(airlabsService.getAirports()).willReturn(allAirports);

        mvc.perform(get("/airlabs/airports")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].code").value(anaa.getCode()))
                .andExpect(jsonPath("$.data[0].name").value(anaa.getName()));
    }

    @Test
    public void givenCities_whenGetCities_thenReturnJsonArray() throws Exception {
        City prague = City.builder().code("PRG").name("Prague").countryCode("CZ").build();
        List<City> allCities = Arrays.asList(prague);

        given(airlabsService.getCities()).willReturn(allCities);

        mvc.perform(get("/airlabs/cities")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].code").value(prague.getCode()))
                .andExpect(jsonPath("$.data[0].name").value(prague.getName()))
                .andExpect(jsonPath("$.data[0].country_code").value(prague.getCountryCode()));
    }


    @Test
    public void givenCountries_whenGetCountries_thenReturnJsonArray() throws Exception {
        Country australia = new Country("AU", "AUS", 36, "Australia");
        List<Country> allCountries = Arrays.asList(australia);

        given(airlabsService.getCountries()).willReturn(allCountries);

        mvc.perform(get("/airlabs/countries")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].code").value(australia.getCode()))
                .andExpect(jsonPath("$.data[0].code3").value(australia.getCode3()))
                .andExpect(jsonPath("$.data[0].iso_numeric").value(australia.getIsoNumeric()))
                .andExpect(jsonPath("$.data[0].name").value(australia.getName()));
    }

    @Test
    public void givenAirlines_whenGetAirlines_thenReturnJsonArray() throws Exception {
        Airline emirates = new Airline("Emirates", "EK");
        List<Airline> allAirlines = Arrays.asList(emirates);

        given(airlabsService.getAirlines()).willReturn(allAirlines);

        mvc.perform(get("/airlabs/airlines")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].iata_code").value(emirates.getIataCode()))
                .andExpect(jsonPath("$.data[0].name").value(emirates.getName()));
    }

    @Test
    public void whenGetTaxes_thenThrowPremiumAccountOnlyException() throws Exception {
        given(airlabsService.getTaxes()).willThrow(PremiumAccountOnlyException.class);
        mvc.perform(get("/airlabs/taxes")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isBadRequest()));
    }

    @Test
    public void givenAircrafts_whenGetAircrafts_thenReturnJsonArray() throws Exception {
        Aircraft aerospace = new Aircraft("142", "British Aerospace BAe 146-200");
        List<Aircraft> allAIAircrafts = Arrays.asList(aerospace);

        given(airlabsService.getAircrafts()).willReturn(allAIAircrafts);

        mvc.perform(get("/airlabs/aircrafts")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].code").value(aerospace.getCode()))
                .andExpect(jsonPath("$.data[0].name").value(aerospace.getName()));
    }

    @Test
    public void whenGetAirplanes_thenThrowPremiumAccountOnlyException() throws Exception {
        given(airlabsService.getAirplanes()).willThrow(PremiumAccountOnlyException.class);
        mvc.perform(get("/airlabs/airplanes")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isBadRequest()));
    }

    @Test
    public void givenRoutes_whenGetRoutes_thenReturnJsonArray() throws Exception {
        Route route = new Route("1038", "0B");
        List<Route> allRoutes = Arrays.asList(route);

        given(airlabsService.getRoutes()).willReturn(allRoutes);

        mvc.perform(get("/airlabs/routes")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].airline_iata").value(route.getAirlineIATA()))
                .andExpect(jsonPath("$.data[0].flight_number").value(route.getFlightNumber()));
    }

    @Test
    public void givenTimezones_whenGetTimezones_thenReturnJsonArray() throws Exception {
        Timezone timezone = new Timezone("Asia/Ho_Chi_Minh", "VN", 7, 7);
        List<Timezone> allTimezones = Arrays.asList(timezone);

        given(airlabsService.getTimezones()).willReturn(allTimezones);

        mvc.perform(get("/airlabs/timezones")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].timezone").value(timezone.getTimezone()))
                .andExpect(jsonPath("$.data[0].country_code").value(timezone.getCountryCode()))
                .andExpect(jsonPath("$.data[0].gtm").value(timezone.getGtm()))
                .andExpect(jsonPath("$.data[0].dst").value(timezone.getDst()));
    }

    @Test
    public void givenAirports_whenGetAutoComplete_thenReturnJsonObject() throws Exception {
        Airport mad = new Airport("MAD", "Barajas", "Spain");
        Airport toj = new Airport("TOJ", "Torrejon AFB", "Spain");
        Airport xoc = new Airport("XOC", "Atocha Railway St.", "Spain");
        Airport xti = new Airport("XTI", "Chamartin Railway St.", "Spain");
        City madrid = new City("MAD", "Madrid", "Spain");
        List<Airport> airports = Arrays.asList(mad);
        List<Airport> airportsByCities = Arrays.asList(mad, toj, xoc, xti);
        List<City> cities = Arrays.asList(madrid);

        AutoComplete autoComplete = new AutoComplete(
                new ArrayList<>(), cities, airports, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), airportsByCities);

        final String query = "madrid";

        given(airlabsService.getAutoComplete(query)).willReturn(autoComplete);

        mvc.perform(get("/airlabs/autocomplete")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("query", query))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.airports", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data.airports[0].code").value(mad.getCode()))
                .andExpect(jsonPath("$.data.airports[0].name").value(mad.getName()))
                .andExpect(jsonPath("$.data.airports[0].country_name").value(mad.getCountryName()))
                .andExpect(jsonPath("$.data.cities", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data.airports_by_cities", Matchers.hasSize(4)));
    }

    @Test
    public void givenAirports_whenGetNearby_thenReturnJsonArray() throws Exception {
        Airport mad = new Airport("MAD", "Barajas", "Spain");
        Airport toj = new Airport("TOJ", "Torrejon AFB", "Spain");
        Airport xoc = new Airport("XOC", "Atocha Railway St.", "Spain");
        Airport xti = new Airport("XTI", "Chamartin Railway St.", "Spain");
        List<Airport> airports = Arrays.asList(mad, toj, xoc, xti);

        final double latQuery = -6.1744;
        final double lngQuery = 106.8294;
        final double distanceQuery = 1000;

        given(airlabsService.getNearby(latQuery, lngQuery, distanceQuery)).willReturn(airports);

        mvc.perform(get("/airlabs/nearby")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("lat", String.valueOf(latQuery))
                .param("lng", String.valueOf(lngQuery))
                .param("distance", String.valueOf(distanceQuery)))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data", Matchers.hasSize(4)))
                .andExpect(jsonPath("$.data[0].code").value(mad.getCode()))
                .andExpect(jsonPath("$.data[1].code").value(toj.getCode()))
                .andExpect(jsonPath("$.data[2].code").value(xoc.getCode()))
                .andExpect(jsonPath("$.data[3].code").value(xti.getCode()));
    }

    @Test
    public void whenGetFlights_thenThrowPremiumAccountOnlyException() throws Exception {
        given(airlabsService.getFlights()).willThrow(PremiumAccountOnlyException.class);
        mvc.perform(get("/airlabs/flights")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isBadRequest()));
    }

    @Test
    public void whenGetTimetables_thenThrowPremiumAccountOnlyException() throws Exception {
        given(airlabsService.getTimetable()).willThrow(PremiumAccountOnlyException.class);
        mvc.perform(get("/airlabs/timetable")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect((status().isBadRequest()));
    }
}
