package com.perrysetgo.illageSummerCamp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
//import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.aboutCampButton) Button mAboutCampButton;
    @Bind(R.id.campMapButton) Button mCampMapButton;
    @Bind(R.id.nextActivityButton) Button mNextActivityButton;
    @Bind(R.id.allActivitiesButton) Button mViewAllButton;
    @Bind(R.id.contactUsButton) Button mContactUsButton;
    @Bind(R.id.adminButton) Button adminButton;
    @Bind(R.id.logoutButton) Button logoutButton;
    @Bind(R.id.addEventButton) Button addActivityButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private boolean loggedIn;
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        loggedIn = mSharedPreferences.getBoolean(Constants.PREFERENCES_LOGIN_STATUS, true);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //this is here to circumvent needing to log in. REMOVE BEFORE PRODUCTION
        addActivityButton.setVisibility(View.VISIBLE);
        adminButton.setVisibility(View.VISIBLE);
        adminButton.setVisibility(View.VISIBLE);

        // TODO: 7/6/16 fix login/registration and switches
        //todo push notifications??
        //todo save events to "my events"
        //todo upload photos?
        //todo see participants
        //todo contact participants
        //todo navigation drawer

        if (loggedIn) {
            Log.d(TAG, "logged in!");
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
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putBoolean(Constants.PREFERENCES_LOGIN_STATUS, false).apply();
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

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
