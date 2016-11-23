package com.perrysetgo.illageSummerCamp.models;

public class Location {
    private String mName;
    private String mAddress;
    private double mLongitude;
    private double mLatitude;
    private String mDescription;

    public Location(String name, String address, double latitude, double longitude, String description) {
        mName = name;
        mAddress = address;
        mLatitude = latitude;
        mLongitude = longitude;
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


    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public String getDescription() {
        return mDescription;
    }

}
