package com.perrysetgo.illageSummerCamp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.fragments.SignUpFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.signInButton) Button signInButton;
    @Bind(R.id.signUpButton) Button signUpButton;

    // TODO: 7/6/16 fix login/registration and switches
    //todo push notifications??
    //todo save events to "my events"
    //todo see participants
    //todo contact participants
    //// TODO: 11/15/16 fix navigation to have better UX on all activities.


    //shared pref & login
    private boolean loggedInAsAdmin;
    private boolean showSignOnDialog;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public Context context;

    //drawer
    private ListView mDrawerList;
    private ArrayAdapter<String> navDrawAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    //dialog
    private class DrawerItemClickListener implements ListView.OnItemClickListener, View.OnClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            switch (position) {
                case 0: {
                    Intent intent = new Intent(MainActivity.this, AboutCampActivity.class);
                    startActivity(intent);
                    break;
                }
                case 1: {
                    Intent intent = new Intent(MainActivity.this, IllageMapActivity.class);
                    startActivity(intent);
                    break;
                }
                case 2: {
                    Intent intent = new Intent(MainActivity.this, NextEventActivity.class);
                    startActivity(intent);
                    break;
                }
                case 3: {
                    Intent intent = new Intent(MainActivity.this, AllEventsActivity.class);
                    startActivity(intent);
                    break;
                }
                case 4: {
                    Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                    startActivity(intent);
                    break;
                }
                case 5: {
                    Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                    startActivity(intent);
                    break;
                }
                case 6: {
                    Intent intent = new Intent(MainActivity.this, AddPhotoActivity.class);
                    startActivity(intent);
                    break;
                }
                case 7: {
                    Intent intent = new Intent(MainActivity.this, PhotoGalleryActivity.class);
                    startActivity(intent);
                    break;
                }
                case 8:{
                    Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(intent);
                    break;
                }
                case 9: {
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    break;
                }

                default:
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
            mDrawerLayout.closeDrawer(mDrawerList);
        }

        @Override
        public void onClick(View view) {

            if (view == signInButton){
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
            if (view == signUpButton){
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        showSignOnDialog =  mSharedPreferences.getBoolean(Constants.PREFERENCES_SHOW_SIGN_ON_DIALOG,true); //OK with seeing sign in/up dialog?
        loggedInAsAdmin = mSharedPreferences.getBoolean(Constants.PREFERENCES_USER_LOGIN_STATUS, true); //logged in as admin?

        if (!showSignOnDialog) {
            SignUpFragment signupFragment = new SignUpFragment();
            signupFragment.show(getFragmentManager(), "Sample Fragment");
        }

        context = getApplicationContext();

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ButterKnife.bind(this);

        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    //nav drawer start.
    private void addDrawerItems(){
        String[] osArray = { "About Camp", "Camp Map", "See Next Event", "See All Events", "Contact Us", "Add Event", "Upload Photo", "See All Photos", "Sign In", "Sign Up","Admin Panel" };//// TODO: 11/15/16 find a way to make this more dynamic and/or retrieve prgrammatically
        navDrawAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(navDrawAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("Back");
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.setScrimColor(Color.argb(140, 194, 194, 194));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.string.action_setting) {
            return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.action_logout) { //handle admin logout click
            logAdminOut();
            finish();
            startActivity(getIntent());
            Log.d(TAG, "admin logout button was clicked");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //nav drawer end


    @Override //this should only show if admin is logged in!
    public boolean onCreateOptionsMenu(Menu menu) {
        if(loggedInAsAdmin) {
            Log.d(TAG, "LOGGED IN - SHOW MENU");
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
        }
        else
        {
            Log.d(TAG, "not logged in as admin");
        }
        return super.onCreateOptionsMenu(menu);
    }





//    private void logUserOut() {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//    }

    private void logAdminOut(){
        mEditor.putBoolean(Constants.PREFERENCES_USER_LOGIN_STATUS, false).apply();
    }
}
