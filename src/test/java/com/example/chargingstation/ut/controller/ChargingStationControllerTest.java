package com.example.chargingstation.ut.controller;


import com.example.chargingstation.controller.ChargingStationController;
import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.service.ChargingStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.hamcrest.Matchers;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ChargingStationController.class)
public class ChargingStationControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ChargingStationController chargingStationController;

    @MockBean
    private ChargingStationService chargingStationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(chargingStationController).build();
    }

    @Test   //done
    public void whenFindByAll_thenAllObjectsAreReturned() throws Exception {

        ChargingStation firstChargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));
        ChargingStation secondChargingStation = new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)));

        List<ChargingStation> allChargingStations = Arrays.asList(firstChargingStation, secondChargingStation);

        given(chargingStationService.findAllChargingStations()).willReturn(allChargingStations);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations/")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].zipCode", is(firstChargingStation.getZipCode())));

        verify(chargingStationService, VerificationModeFactory.times(1)).findAllChargingStations();
        reset(chargingStationService);
    }

    @Test  //done
    public void whenFindById_thenOneObjectIsReturned() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations/5c61cd57416326b7e4195b98")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test  //done maybe
    public void whenFindByValidZipCode_thenOneObjectIsReturned() throws Exception {

        ChargingStation chargingStation = new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)));

        List<ChargingStation> chargingStations = Arrays.asList(chargingStation);

        given(chargingStationService.findChargingStationByZipCode(Mockito.any())).willReturn(chargingStations);

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations/zipcode/10589")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$", hasSize(1)));
//            .andExpect(jsonPath("$.id").exists());
//            .andExpect(jsonPath("$.zipCode", Matchers.equalTo("10589")));

        verify(chargingStationService, VerificationModeFactory.times(1)).findChargingStationByZipCode(Mockito.any());
        reset(chargingStationService);
    }

    @Test
    public void whenFindByGeoLocation_thenObjectIsReturned() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations?longitude=13.400954&latitude=52.520008&distance=10.0")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test  //done
    public void saveChargingStation_validUser_ChargingStationIsReturned() throws Exception {
        ChargingStation chargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));

        given(chargingStationService.addChargingStations(Mockito.any())).willReturn(chargingStation);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/v1/chargingstations")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(MAPPER.writeValueAsString(chargingStation)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.zipCode", Matchers.equalTo("10178")));

        verify(chargingStationService, VerificationModeFactory.times(1)).addChargingStations(Mockito.any());
        reset(chargingStationService);
    }
}
