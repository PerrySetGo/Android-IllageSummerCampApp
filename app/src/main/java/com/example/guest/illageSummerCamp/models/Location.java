package com.example.guest.illageSummerCamp.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.lang.reflect.Array;

@Table(name = "locations", id = "_id")
public class Location extends Model {

    @Column (name = "LocName")
    private String mLocationName;

    @Column (name = "LocAddress")
    private String mLocationAddress;

    @Column (name = "LocDescrip")
    private String mLocDescrip;

    private double mLocationLatitude;
    private double mLocationLongitude;

    public Location() {
        super();
    }

    public Location(String locationName, String locationAddress, String locDescrip, double locationLatitude, double locationLongitude) {
        super();
        mLocationName = locationName;
        mLocationAddress = locationAddress;
        mLocDescrip = locDescrip;
        mLocationLatitude = locationLatitude;
        mLocationLongitude = locationLongitude;
    }

    public String getLocationName() {
        return mLocationName;
    }

    public void setLocationName(String locationName) {
        mLocationName = locationName;
    }

    public String getLocationAddress() {
        return mLocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        mLocationAddress = locationAddress;
    }

    public String getLocDescrip() {
        return mLocDescrip;
    }

    public void setLocDescrip(String locDescrip) {
        mLocDescrip = locDescrip;
    }

    public double getLocationLatitude() {
        return mLocationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        mLocationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return mLocationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        mLocationLongitude = locationLongitude;
    }
}
