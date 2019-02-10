package com.example.chargingstation.controller;


import com.example.chargingstation.model.ChargingStation;
import com.example.chargingstation.model.ChargingStationDto;
import com.example.chargingstation.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chargingstations")
public class ChargingStationController {

    @Autowired
    private ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    /** GET request to return all appointments **/
    @RequestMapping(method = RequestMethod.GET)
    List<ChargingStation> getAllChargingStations() {
        return chargingStationService.findAllChargingStations();
    }

    /** GET request to return specific charging stations based on ID **/
    @RequestMapping(path = "/{chargingStationId}", method = RequestMethod.GET)
    public Optional<ChargingStation> getChargingStationById(@PathVariable String chargingStationId) {
        return chargingStationService.findChargingStationById(chargingStationId);
    }

    /** GET request to return specific charging stations based on Zip Code **/
    @RequestMapping(path = "/zipcode/{zipCode}", method = RequestMethod.GET)
    public List<ChargingStation> getChargingStationByZipCode(@PathVariable String zipCode) {
        return chargingStationService.findChargingStationByZipCode(zipCode);
    }

    /** POST request to add new charging station **/
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ChargingStation addChargingStations(@RequestBody ChargingStationDto chargingStationDto) {
        return chargingStationService.addChargingStations(chargingStationDto);
    }

}