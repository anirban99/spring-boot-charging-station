package com.example.chargingstation.ut.controller;


import com.example.chargingstation.controller.ChargingStationController;
import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.model.ChargingStationInfo;
import com.example.chargingstation.service.ChargingStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Test
    public void whenFindByAll_thenAllObjectsAreReturned() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations/")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void whenFindById_thenOneObjectIsReturned() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations/5c61cd57416326b7e4195b98")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void whenFindByValidZipCode_thenOneObjectIsReturned() throws Exception {

//        ChargingStation chargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));
//        given(chargingStationService.findChargingStationByZipCode("10178")).willReturn();

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations/zipcode/10178")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void whenFindByGeoLocation_thenObjectIsReturned() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/chargingstations?longitude=13.400954&latitude=52.520008&distance=10.0")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void saveChargingStation_validUser_ChargingStationIsReturned() throws Exception {
        ChargingStation chargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));

        ChargingStationInfo chargingStationInfo = new ChargingStationInfo();
        chargingStationInfo.setZipCode("10178");
        chargingStationInfo.setLongitude("13.404954");
        chargingStationInfo.setLatitude("52.525008");


        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/v1/chargingstations")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(MAPPER.writeValueAsString(chargingStationInfo)))
            .andExpect(status().isCreated());
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.zipCode", Matchers.equalTo("10178")));
    }
}
