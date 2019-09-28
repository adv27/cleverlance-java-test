package com.cleverlance.airlabs;

import com.cleverlance.airlabs.controller.AirlabsController;
import com.cleverlance.airlabs.dao.AirportDAO;
import com.cleverlance.airlabs.entity.airlabs.Airport;
import com.cleverlance.airlabs.service.AirlabsService;
import com.cleverlance.airlabs.service.AirportService;
import com.cleverlance.airlabs.service.PingService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AirlabsController.class)
public class PingTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AirlabsController subject;

    @MockBean
    private AirportService airportService;

    @MockBean
    private AirlabsService airlabsService;

    @MockBean
    private PingService pingService;

    @MockBean
    private AirportDAO airportDAO;

    @Rule
    public final OutputCapture outputCapture = new OutputCapture();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        PrintStream sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void givenAirports_whenPing_thenSystemOut() throws Exception {
//        PrintStream out = mock(PrintStream.class);
//        System.setOut(out);


        Airport hn = new Airport("HN", "Ha Noi");
        Airport hcm = new Airport("HCM", "Ho Chi Minh");
        List<Airport> airports = Arrays.asList(hn, hcm);

        given(airportDAO.findAll()).willReturn(airports);
        given(airportService.listAllAirports()).willReturn(airports);

        mvc.perform(get("/airlabs/ping"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

//        System.out.println("Hello world");

        assertThat(outContent.toString())
                .isEqualTo(airportDAO.findAll().toString());

        assertThat("Hello world")
                .isEqualTo(airportDAO.findAll().toString());
    }
}