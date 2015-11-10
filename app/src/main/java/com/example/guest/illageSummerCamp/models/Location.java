package com.example.guest.illageSummerCamp.models;

import java.util.ArrayList;

/**
 * Created by Guest on 11/9/15.
 */

public class Location {
    private String mName;
    private String mAddress;
    private double mLongitude;
    private double mLatitude;
    private String mDescription;

    public Location(String name, String address, double longitude, double latitude, String description) {
        mName = name;
        mAddress = address;
        mLongitude = longitude;
        mLatitude = latitude;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
