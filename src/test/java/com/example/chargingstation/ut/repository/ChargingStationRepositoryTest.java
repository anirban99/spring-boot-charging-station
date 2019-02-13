package com.example.chargingstation.ut.repository;

import com.example.chargingstation.repository.ChargingStationRepository;
import com.example.chargingstation.model.ChargingStation;
import com.mongodb.DBCollection;
import org.apache.catalina.core.ApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.notNullValue;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataMongoTest
public class ChargingStationRepositoryTest {

//    @Autowired
//    private TestEntityManager entityManager;


    String collectionName;
    ChargingStation chargingStation;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private ChargingStationRepository chargingStationRepository;

//    @Autowired
//    private ApplicationContext applicationContext;


    @Test
    public void testStuff() {
        System.out.println(mongoTemplate);

//        ChargingStation chargingStation = new ChargingStation();
        Criteria criteria = new Criteria();

        List<Object> find = mongoTemplate.find(new Query(criteria), Object.class, "data");

        System.out.println(find.size());

        for (Object object : find) {
            System.out.println(object);
        }
    }

//    @Before
//    public void before() {
//        collectionName = "logs";
//        chargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));
//    }
//
//    @After
//    public void after() {
//        mongoTemplate.dropCollection(collectionName);
//    }

//    @Test
//    public void checkMongoTemplate() {
//        assertNotNull(mongoTemplate);
//        DBCollection createdCollection = mongoTemplate.createCollection(collectionName);
//        assertTrue(mongoTemplate.collectionExists(collectionName));
//    }

//    @Before
//    public void init() {
//
//        chargingStationRepository.deleteAll();
//
//        chargingStationRepository.save(new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008))));
//        chargingStationRepository.save(new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008))));
//        chargingStationRepository.save(new ChargingStation("10179", new GeoJsonPoint(Double.valueOf(13.406954),Double.valueOf(52.513008))));
//    }

//    @Test
//    public void whenFindByZipCode_thenChargingStationShouldBeFound(){
//
////        ChargingStation firstChargingStation = new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)));
////        ChargingStation secondChargingStation = new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)));
////
////        when(chargingStationRepository.findByZipCode("10178")).thenReturn(Arrays.asList(firstChargingStation));
//
//        List<ChargingStation> chargingStation = chargingStationRepository.findByZipCode("10178");
////        assertThat(result).hasSize(1).extracting(ChargingStation::getZipCode).contains(firstChargingStation.getZipCode());
//        assertThat(chargingStation).hasSize(1);
//
//
//        mongoTemplate.save(chargingStation, collectionName);
//
//        List<ChargingStation> chargingStation = mongoTemplate.findById()
//    }




//    @Test
//    public void countAllChargingStations() {
//
//        List<ChargingStation> chargingStations = chargingStationRepository.findAll();
//
//        assertThat(chargingStations).hasSize(3);
//        assertEquals(3, chargingStations.size());
//    }
//
//
//    @Test
//    public void findOneChargeStation() {
//
////        Example<ChargingStation> example = Example.of(new ChargingStation("12349", new GeoJsonPoint(Double.valueOf(11.72),Double.valueOf(51.12))));
//
//        List<ChargingStation> chargingStation = chargingStationRepository.findByZipCode("12345");
//        assertEquals(1, chargingStation.size());
//    }


}
