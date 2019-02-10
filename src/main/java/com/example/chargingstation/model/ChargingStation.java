package com.example.chargingstation.model;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;


@Document(collection = "charging_stations")
public class ChargingStation {

    private @Id String id;
    private String zipCode;
    private GeoJsonPoint location;

    public ChargingStation(String zipCode, GeoJsonPoint location) {
        this.zipCode = zipCode;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final ChargingStation that = (ChargingStation) o;
        return Objects.equals(this.getId(), that.getId()) &&
            Objects.equals(this.getZipCode(), that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getLocation());
    }
}
