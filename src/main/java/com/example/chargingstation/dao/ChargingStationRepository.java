package com.example.chargingstation.dao;

import com.example.chargingstation.model.ChargingStation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChargingStationRepository extends MongoRepository<ChargingStation, String> {

    List<ChargingStation> findByZipCode(String zipCode);

}
