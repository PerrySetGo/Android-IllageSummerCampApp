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


//
//
//
//        LatLng peninsulaPark = new LatLng(45.5697276,-122.6753081);
//        LatLng floridaRoom = new LatLng(45.5629381,-122.6730989);
//        LatLng skidmoreBluffs = new LatLng (45.5550166,-122.6925705);
//        LatLng dynasty = new LatLng(45.562471,-122.677954);
//        LatLng lesbianLake = new LatLng(45.334031,-122.474941);
//        LatLng illageAlley = new LatLng (45.5631991,-122.6716721);
//
//
//        Marker peninsulaMarker = mMap.addMarker(new MarkerOptions().position(peninsulaPark).title("Peninsula Park").snippet("700 N Rosa Parks Way"));
//        Marker floridaMarker = mMap.addMarker(new MarkerOptions().position(floridaRoom).title("Florida Room").snippet("435 N Killingsworth St"));
//        Marker skidmoreMarker = mMap.addMarker(new MarkerOptions().position(skidmoreBluffs).title("Skidmore Bluffs").snippet("2230 N Skidmore Ct"));
//        Marker dynastyMarker = mMap.addMarker(new MarkerOptions().position(dynasty).title("Dynasty").snippet("832 N Killingsworth St"));
//        Marker lesbianMarker = mMap.addMarker(new MarkerOptions().position(lesbianLake).title("lesbianLake").snippet("Please register with a camp counsellor to get the address."));
//        Marker illageMarker = mMap.addMarker(new MarkerOptions().position(illageAlley).title("illageAlley").snippet("5527 N Vancouver Ave"));
//
//        builder.include(peninsulaMarker.getPosition());
//        builder.include(floridaMarker.getPosition());
//        builder.include(skidmoreMarker.getPosition());
//        builder.include(dynastyMarker.getPosition());
//        builder.include(illageMarker.getPosition());

        LatLngBounds bounds = builder.build();

        int padding = 50; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);
    }
}
