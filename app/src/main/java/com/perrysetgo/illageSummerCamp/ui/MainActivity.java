package com.perrysetgo.illageSummerCamp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.perrysetgo.illageSummerCamp.R;
//import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.aboutCampButton)  Button mAboutCampButton;
    @Bind(R.id.campMapButton)  Button mCampMapButton;
    @Bind(R.id.nextActivityButton) Button mNextActivityButton;
    @Bind(R.id.allActivitiesButton) Button mViewAllButton;
    @Bind(R.id.contactUsButton) Button mContactUsButton;
    @Bind(R.id.adminButton) Button adminButton;
    @Bind(R.id.logoutButton) Button logoutButton;
    @Bind(R.id.addEventButton) Button addActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //this is here to circumvent needing to log in. REMOVE BEFORE PRODUCTION
        addActivityButton.setVisibility(View.VISIBLE);
        adminButton.setVisibility(View.INVISIBLE);

//        if (isRegistered()) {
//            addActivityButton.setVisibility(View.VISIBLE);
//            logoutButton.setVisibility(View.VISIBLE);
//            adminButton.setVisibility(View.INVISIBLE);
//        }

        mAboutCampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutCampActivity.class);
                startActivity(intent);
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseUser.logOut();
//                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
//                finish();
//                startActivity(getIntent());
//            }
//        });

//        mCampMapButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, IllageMapActivity.class);
//                startActivity(intent);
//            }
//        });

        mViewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllEventsActivity.class);
                startActivity(intent);
            }
        });

        mNextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NextEventActivity.class);
                startActivity(intent);
            }
        });

        mContactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

    }
    //// TODO: 6/30/16 fix user registration to be able to admin things and show/hide admin panel.
    //

//    private boolean isRegistered() {
//        ParseUser currentUser = ParseUser.getCurrentUser();
//        if (currentUser == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }

}
