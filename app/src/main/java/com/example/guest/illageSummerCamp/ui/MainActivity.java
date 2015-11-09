package com.example.guest.illageSummerCamp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.models.User;

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


    private User mUser;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPreferences = getApplicationContext().getSharedPreferences("illageCamp", Context.MODE_PRIVATE);

        if (isRegistered()) {
            addActivityButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
            adminButton.setVisibility(View.INVISIBLE);
        }

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
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                startActivity(getIntent());
            }
        });

        mCampMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IllageMapActivity.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(MainActivity.this,ContactActivity.class);
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

    private boolean isRegistered() {
        String username = mPreferences.getString("username", null);
        if (username == null) {
            return false;
        } else {
            setUser(username);
            return true;
        }
    }

    private void setUser(String username) {
        User user = User.find(username);
        if (user != null) {
            mUser = user;
        } else {
            mUser = new User(username);
            mUser.save();
        }
        Toast.makeText(this, "Welcome " + mUser.getName(), Toast.LENGTH_LONG).show();
    }
}
