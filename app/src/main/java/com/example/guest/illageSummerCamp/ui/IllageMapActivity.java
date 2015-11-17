package com.example.guest.illageSummerCamp.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.models.Location;
import com.example.guest.illageSummerCamp.models.LocationLib;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class IllageMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Location> mLocations;
    private LocationLib mLocationLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illage_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mLocationLib = new LocationLib();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (int i = 0; i < mLocationLib.getLocations().size(); i++ ) {
            LatLng currentLatLng = new LatLng(mLocationLib.getLocations().get(i).getLatitude(), mLocationLib.getLocations().get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentLatLng).title(mLocationLib.getLocations().get(i).getName()).snippet(mLocationLib.getLocations().get(i).getAddress()));
            builder.include(currentLatLng);

        }

        LatLngBounds bounds = builder.build();

        int padding = 50; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);
    }
}
