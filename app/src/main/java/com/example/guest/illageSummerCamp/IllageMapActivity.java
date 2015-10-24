package com.example.guest.illageSummerCamp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class IllageMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illage_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //dryify!!
        mMap = googleMap;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        // Add a marker in Sydney and move the camera
        LatLng peninsulaPark = new LatLng(45.5697276,-122.6753081);
        LatLng floridaRoom = new LatLng(45.5629381,-122.6730989);
        LatLng skidmoreBluffs = new LatLng (45.5550166,-122.6925705);
        LatLng dynasty = new LatLng(45.562471,-122.677954);
        LatLng lesbianLake = new LatLng(45.334031,-122.474941);
        LatLng illageAlley = new LatLng (45.5631991,-122.6716721);
        Marker peninsulaMarker = mMap.addMarker(new MarkerOptions().position(peninsulaPark).title("Peninsula Park"));
        Marker floridaMarker = mMap.addMarker(new MarkerOptions().position(floridaRoom).title("Florida Room"));
        Marker skidmoreMarker = mMap.addMarker(new MarkerOptions().position(skidmoreBluffs).title("Skidmore Bluffs"));
        Marker dynastyMarker = mMap.addMarker(new MarkerOptions().position(dynasty).title("Dynasty"));
        //Marker lesbianMarker = mMap.addMarker(new MarkerOptions().position(lesbianLake).title("lesbianLake"));
        Marker illageMarker = mMap.addMarker(new MarkerOptions().position(illageAlley).title("illageAlley"));

        builder.include(peninsulaMarker.getPosition());
        builder.include(floridaMarker.getPosition());
        builder.include(skidmoreMarker.getPosition());
        builder.include(dynastyMarker.getPosition());
        //builder.include(lesbianMarker.getPosition());
        builder.include(illageMarker.getPosition());

        LatLngBounds bounds = builder.build();

        int padding = 50; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);
    }
}
