package com.cleverlance.airlabs;

import com.cleverlance.airlabs.controller.AirlabsController;
import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.repository.AirportRepository;
import com.cleverlance.airlabs.service.AirlabsConsumerService;
import com.cleverlance.airlabs.service.PingService;
import com.cleverlance.airlabs.service.impl.AirlabsConsumerServiceImpl;
import com.cleverlance.airlabs.service.impl.PingServiceImpl;
import org.hamcrest.Matchers;
import org.hamcrest.text.IsEmptyString;
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
        AirlabsConsumerService airlabsService() {
            return new AirlabsConsumerServiceImpl();
        }

    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirportRepository airportRepository;

    @MockBean
    private AirlabsConsumerService airlabsConsumerService;

    @MockBean
    private PingService pingService;

    @Test
    public void whenPing_thenReturnStatusOk() throws Exception {
        mvc.perform(get("/airlabs/ping"))
                .andExpect((status().isOk()))
                .andExpect(content().string(IsEmptyString.isEmptyOrNullString()));
    }

    @Test
    public void givenAirports_whenGetAirports_thenReturnJsonArray() throws Exception {
        Airport anaa = new Airport("AAA", "Anaa");
        Airport arrabury = new Airport("AAB", "Arrabury");
        List<Airport> allAirports = Arrays.asList(anaa, arrabury);

        given(airlabsConsumerService.getAirports()).willReturn(allAirports);

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

        given(airlabsConsumerService.getAirportByCode(codeQuery)).willReturn(arrabury);

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
    public void givenAirports_whenGetAirportByEmptyCode_thenReturnJsonArray() throws Exception {
        Airport anaa = new Airport("AAA", "Anaa");
        Airport arrabury = new Airport("AAB", "Arrabury");
        List<Airport> allAirports = Arrays.asList(anaa, arrabury);

        final String codeQuery = "";

        given(airlabsConsumerService.getAirports()).willReturn(allAirports);

        mvc.perform(get("/airlabs/airports")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("code", codeQuery))
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
    public void givenAirports_whenGetAirportByWrongCode_thenReturnJsonEmptyObject() throws Exception {
        final String codeQuery = "wrong_code";

        given(airlabsConsumerService.getAirportByCode(codeQuery)).willReturn(null);

        mvc.perform(get("/airlabs/airports")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("code", codeQuery))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data").isEmpty());
    }


    @Test
    public void givenAirport_whenGetAirports_thenReturnJsonArray() throws Exception {
        Airport anaa = new Airport("AAM", "Mala Mala");
        List<Airport> allAirports = Arrays.asList(anaa);

        given(airlabsConsumerService.getAirports()).willReturn(allAirports);

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
}
