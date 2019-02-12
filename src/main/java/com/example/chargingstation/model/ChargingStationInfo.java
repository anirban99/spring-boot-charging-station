package com.example.chargingstation.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "charging_stations")
public class ChargingStationInfo {

    private String zipCode;
    private String longitude;
    private String latitude;

    public ChargingStationInfo() {
    }

    public ChargingStationInfo(String zipCode, String longitude, String latitude) {
        this.zipCode = zipCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }
}
