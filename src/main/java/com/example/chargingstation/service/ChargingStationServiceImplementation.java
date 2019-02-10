package com.example.chargingstation.service;

import com.example.chargingstation.dao.ChargingStationRepository;
import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.model.ChargingStationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("chargingStationService")
public class ChargingStationServiceImplementation implements ChargingStationService{

    @Autowired
    ChargingStationRepository chargingStationRepository;

    public ChargingStationServiceImplementation(ChargingStationRepository chargingStationRepository) {
        this.chargingStationRepository = chargingStationRepository;
    }

    @Override
    public List<ChargingStation> findAllChargingStations() {
        return chargingStationRepository.findAll();
    }

    @Override
    public Optional<ChargingStation> findChargingStationById(String appointmentId) {
        return chargingStationRepository.findById(appointmentId);
    }

    @Override
    public List<ChargingStation> findChargingStationByZipCode(String zipCode) {
        return chargingStationRepository.findByZipCode(zipCode);
    }

    @Override
    public List<ChargingStation> findChargingStationByGeoLocation(String longitude, String latitude, double distance) {

        return chargingStationRepository.findByLocationNear(
                new Point(Double.valueOf(longitude), Double.valueOf(latitude)),
                new Distance(distance, Metrics.KILOMETERS));
    }

    @Override
    public ChargingStation addChargingStations(ChargingStationDto chargingStationDto) {
        return chargingStationRepository.save(new ChargingStation(chargingStationDto.getZipCode(),
                new GeoJsonPoint(Double.parseDouble(chargingStationDto.getLongitude()), Double.parseDouble(chargingStationDto.getLatitude()))));
    }
}