package com.example.chargingstation.service;

import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.model.ChargingStationDto;

import java.util.List;
import java.util.Optional;

public interface ChargingStationService {

    List<ChargingStation> findAllChargingStations();

    Optional<ChargingStation> findChargingStationById(String appointmentId);

    List<ChargingStation> findChargingStationByZipCode(String zipCode);

    ChargingStation addChargingStations(ChargingStationDto chargingStationDto);

}