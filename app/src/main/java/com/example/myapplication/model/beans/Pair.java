package com.example.myapplication.model.beans;

/**
 * Created by user on 3/15/17.
 */

public class Pair {
    private Double latitude;
    private Double longitude;

    public Pair(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String generateCoordinate() {
        return latitude + "," + longitude;
    }
}
