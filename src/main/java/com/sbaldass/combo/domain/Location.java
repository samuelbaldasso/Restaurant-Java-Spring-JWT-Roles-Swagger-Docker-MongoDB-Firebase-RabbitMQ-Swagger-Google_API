package com.sbaldass.combo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

