package com.example.chargingstation.ut.service;

import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.repository.ChargingStationRepository;
import com.example.chargingstation.service.ChargingStationService;
import com.example.chargingstation.service.ChargingStationServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ChargingStationServiceImplementationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public ChargingStationService chargingStationService() {
            return new ChargingStationServiceImplementation();
        }
    }

    @Autowired
    private ChargingStationService chargingStationService;

    @MockBean
    private ChargingStationRepository chargingStationRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void whenFindAll_thenReturnAllRecords(){

        ChargingStation firstChargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));
        ChargingStation secondChargingStation = new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)));
        ChargingStation thirdChargingStation = new ChargingStation("10179", new GeoJsonPoint(Double.valueOf(13.406954),Double.valueOf(52.513008)));
        List<ChargingStation> allChargingStations = Arrays.asList(firstChargingStation, secondChargingStation, thirdChargingStation);

        when(chargingStationRepository.findAll()).thenReturn(allChargingStations);

        List<ChargingStation> result = chargingStationService.findAllChargingStations();
        assertEquals(3, result.size());
    }

    @Test
    public void whenFindById_thenChargingStationShouldBeFound(){

        ChargingStation chargingStation = new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)));
        chargingStation.setId("11L");

        when(chargingStationRepository.findById(chargingStation.getId())).thenReturn(Optional.of(chargingStation));

        Optional<ChargingStation> result = chargingStationService.findChargingStationById("11L");
        assertThat(result).isNotEmpty();
    }

    @Test
    public void whenFindByZipCode_thenChargingStationShouldBeFound(){

        ChargingStation firstChargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));
        ChargingStation secondChargingStation = new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)));

        when(chargingStationRepository.findByZipCode("10178")).thenReturn(Arrays.asList(firstChargingStation));

        List<ChargingStation> result = chargingStationService.findChargingStationByZipCode("10178");
        assertThat(result).hasSize(1).extracting(ChargingStation::getZipCode).contains(firstChargingStation.getZipCode());
    }

    @Test
    public void whenFindByChargingStationByGeoLocation_thenNearbyChargingStationShouldBeFound(){

        ChargingStation firstChargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));
        ChargingStation secondChargingStation = new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)));

        when(chargingStationRepository.findByLocationNear(
            new Point(Double.valueOf(13.400954), Double.valueOf(52.520008)),
            new Distance(10.0, Metrics.KILOMETERS))).thenReturn(Arrays.asList(firstChargingStation));

        List<ChargingStation> result = chargingStationService.findChargingStationByGeoLocation("13.400954", "52.520008", 10.0);
        assertThat(result).hasSize(1).extracting(ChargingStation::getZipCode).contains(firstChargingStation.getZipCode());
    }
}
