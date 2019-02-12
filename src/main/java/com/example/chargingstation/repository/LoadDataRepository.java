package com.example.chargingstation.repository;

import com.example.chargingstation.model.ChargingStation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Configuration
@Slf4j
public class LoadDataRepository {

    static final private Logger logger = LoggerFactory.getLogger(LoadDataRepository.class);

    @Bean
    CommandLineRunner initDatabase(ChargingStationRepository chargingStationRepository) {
        return args -> {
            chargingStationRepository.deleteAll();
            logger.info("Preloading " + chargingStationRepository.save(new ChargingStation("10178", new GeoJsonPoint(Double.valueOf(13.404954),Double.valueOf(52.525008)))));
            logger.info("Preloading " + chargingStationRepository.save(new ChargingStation("10589", new GeoJsonPoint(Double.valueOf(13.300954),Double.valueOf(52.524008)))));
            logger.info("Preloading " + chargingStationRepository.save(new ChargingStation("10179", new GeoJsonPoint(Double.valueOf(13.406954),Double.valueOf(52.513008)))));
        };
    }
}


// 13.600954 , 52.534008   zip - 12619
// 13.700954 , 52.544008   zip - 15366