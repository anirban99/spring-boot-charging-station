package com.example.chargingstation.ut.dao;

import com.example.chargingstation.repository.ChargingStationRepository;
import com.example.chargingstation.model.ChargingStation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static junit.framework.TestCase.assertEquals;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChargingStationRepositoryTest {

//    @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Before
    public void init() {

        chargingStationRepository.deleteAll();

        chargingStationRepository.save(new ChargingStation("60287", new GeoJsonPoint(Double.valueOf(8.72),Double.valueOf(50.12))));
        chargingStationRepository.save(new ChargingStation("12345", new GeoJsonPoint(Double.valueOf(13.4),Double.valueOf(52.51))));
        chargingStationRepository.save(new ChargingStation("80245", new GeoJsonPoint(Double.valueOf(11.56),Double.valueOf(48.14))));
    }

    @Test
    public void countAllChargingStations() {

        List<ChargingStation> chargingStations = chargingStationRepository.findAll();

        assertThat(chargingStations).hasSize(3);
        assertEquals(3, chargingStations.size());
    }

//    @Test
//    public void countOneChargingStation() {
//
//        Example<ChargingStation> example = Example.of(new ChargingStation("12349", new GeoJsonPoint(Double.valueOf(11.72),Double.valueOf(51.12))));
//
//        assertThat(chargingStationRepository.count(example)).isEqualTo(1L);
//    }

    @Test
    public void findOneChargeStation() {

//        Example<ChargingStation> example = Example.of(new ChargingStation("12349", new GeoJsonPoint(Double.valueOf(11.72),Double.valueOf(51.12))));

        List<ChargingStation> chargingStation = chargingStationRepository.findByZipCode("12345");
        assertEquals(1, chargingStation.size());
    }


}
