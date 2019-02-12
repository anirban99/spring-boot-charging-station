package com.example.chargingstation.service;

import com.example.chargingstation.repository.ChargingStationRepository;
import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.model.ChargingStationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

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
    public ChargingStation addChargingStations(ChargingStationInfo chargingStationInfo) {
        return chargingStationRepository.save(new ChargingStation(chargingStationInfo.getZipCode(),
                new GeoJsonPoint(Double.parseDouble(chargingStationInfo.getLongitude()), Double.parseDouble(chargingStationInfo.getLatitude()))));
    }
}

//https://us-zipcode.api.smartystreets.com/lookup?auth-id=21102174564513388&city=&state="