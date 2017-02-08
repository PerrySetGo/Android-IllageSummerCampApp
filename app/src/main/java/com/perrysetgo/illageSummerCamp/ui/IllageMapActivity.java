package com.perrysetgo.illageSummerCamp.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.Location;
import com.perrysetgo.illageSummerCamp.models.LocationLib;

public class IllageMapActivity extends BaseActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    LocationLib mLocationLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illage_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.setHasOptionsMenu(true);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mLocationLib = new LocationLib();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();


        for (int i = 0; i < mLocationLib.getLocations().size(); i++ ) {
            Location currentLoc = mLocationLib.getLocations().get(i);
            LatLng currentLatLng = new LatLng(currentLoc.getLatitude(), currentLoc.getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentLatLng).title(currentLoc.getName()).snippet(currentLoc.getAddress()+ "\n\n" + currentLoc.getDescription()));
            builder.include(currentLatLng);

        }

        LatLngBounds bounds = builder.build();

        int padding = 50; // offset from edges of the map in px
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(getApplicationContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getApplicationContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getApplicationContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());
                //find a way to put image in snippet? http://stackoverflow.com/questions/18938187/add-an-image-from-url-into-custom-infowindow-google-maps-v2

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_main: return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
