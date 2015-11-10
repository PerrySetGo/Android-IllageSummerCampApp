package com.example.guest.illageSummerCamp.models;

import java.util.ArrayList;

public class LocationLib {

    private ArrayList<Location> mLocations;

    public LocationLib() {
        setLocations();
    }

    private void setLocations() {
        mLocations = new ArrayList<>();

        mLocations.add(new Location(
        "Illage Alley",
        "5527 N Vancouver Ave",
        45.5631991,
        -122.6716721,
        "This is someone's home! Please be respectful."));

        mLocations.add( new Location(
        "Peninsula Park",
        "700 N Rosa Parks Way",
        45.5697276,
        -122.6753081,
        "We will likely be at the gazebo or on the playing fields."));

        mLocations.add( new Location(
        "Florida Room",
        "435 N Killingsworth St",
        45.5629381,
        -122.6730989,
        "This is a 21+ venue."));

        mLocations.add(new Location(
        "Skidmore Bluffs",
        "2230 N Skidmore Ct",
        45.5550166,
        -122.6925705,
        "Please pack out your trash."));

        mLocations.add( new Location(
        "Killingsworth Dynasty",
        "832 N Killingsworth St",
        45.562471,
        -122.677954,
        "This is a 21+ venue."));

        mLocations.add(new Location(
        "Lesbian Lake",
        "Oregon City",
        45.334031,
        -122.474941,
        "Please talk to a camp counsellor to get the address."));
    }

    public ArrayList<Location> getLocations(){
        return mLocations;
    }

}
