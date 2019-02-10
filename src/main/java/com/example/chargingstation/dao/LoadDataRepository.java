package com.example.chargingstation.dao;

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
            logger.info("Preloading " + chargingStationRepository.save(new ChargingStation("60287", new GeoJsonPoint(Double.valueOf(8.72),Double.valueOf(50.12)))));
            logger.info("Preloading " + chargingStationRepository.save(new ChargingStation("12345", new GeoJsonPoint(Double.valueOf(13.4),Double.valueOf(52.51)))));
            logger.info("Preloading " + chargingStationRepository.save(new ChargingStation("80245", new GeoJsonPoint(Double.valueOf(11.56),Double.valueOf(48.14)))));
        };
    }
}
