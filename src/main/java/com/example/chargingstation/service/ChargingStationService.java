package com.example.chargingstation.service;

import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.model.ChargingStationInfo;

import java.util.List;
import java.util.Optional;

public interface ChargingStationService {

    List<ChargingStation> findAllChargingStations();

    Optional<ChargingStation> findChargingStationById(String appointmentId);

    List<ChargingStation> findChargingStationByZipCode(String zipCode);

    List<ChargingStation> findChargingStationByGeoLocation(String longitude, String latitude, double distance);

    ChargingStation addChargingStations(ChargingStationInfo chargingStationInfo);

}