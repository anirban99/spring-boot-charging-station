package com.example.chargingstation.dao;

import com.example.chargingstation.model.ChargingStation;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChargingStationRepository extends MongoRepository<ChargingStation, String> {

    List<ChargingStation> findByZipCode(String zipCode);

    List<ChargingStation> findByLocationNear(Point p, Distance d);

}
