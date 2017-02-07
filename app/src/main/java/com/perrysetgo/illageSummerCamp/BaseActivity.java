package com.perrysetgo.illageSummerCamp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.perrysetgo.illageSummerCamp.ui.AboutCampActivity;
import com.perrysetgo.illageSummerCamp.ui.AddEventActivity;
import com.perrysetgo.illageSummerCamp.ui.AddPhotoActivity;
import com.perrysetgo.illageSummerCamp.ui.AllEventsActivity;
import com.perrysetgo.illageSummerCamp.ui.ContactActivity;
import com.perrysetgo.illageSummerCamp.ui.IllageMapActivity;
import com.perrysetgo.illageSummerCamp.ui.LoginActivity;
import com.perrysetgo.illageSummerCamp.ui.MainActivity;
import com.perrysetgo.illageSummerCamp.ui.NextEventActivity;
import com.perrysetgo.illageSummerCamp.ui.PhotoGalleryActivity;
import com.perrysetgo.illageSummerCamp.ui.SignInActivity;
import com.perrysetgo.illageSummerCamp.ui.SignUpActivity;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    //drawer
    private ListView mDrawerList;
    private ArrayAdapter<String> navDrawAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    public static final String TAG = BaseActivity.class.getSimpleName();


    private class DrawerItemClickListener implements ListView.OnItemClickListener, View.OnClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            switch (position) {
                case 0: {
                    Intent intent = new Intent(getApplicationContext(), AboutCampActivity.class);
                    startActivity(intent);
                    break;
                }
                case 1: {
                    Intent intent = new Intent(getApplicationContext(), IllageMapActivity.class);
                    startActivity(intent);
                    break;
                }
                case 2: {
                    Intent intent = new Intent(getApplicationContext(), NextEventActivity.class);
                    startActivity(intent);
                    break;
                }
                case 3: {
                    Intent intent = new Intent(getApplicationContext(), AllEventsActivity.class);
                    startActivity(intent);
                    break;
                }
                case 4: {
                    Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                    startActivity(intent);
                    break;
                }
                case 5: {
                    Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                    startActivity(intent);
                    break;
                }
                case 6: {
                    Intent intent = new Intent(getApplicationContext(), AddPhotoActivity.class);
                    startActivity(intent);
                    break;
                }
                case 7: {
                    Intent intent = new Intent(getApplicationContext(), PhotoGalleryActivity.class);
                    startActivity(intent);
                    break;
                }
                case 8:{
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                    break;
                }
                case 9: {
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                    break;
                }

                default:
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    break;
            }
            mDrawerLayout.closeDrawer(mDrawerList);
        }

        @Override
        public void onClick(View view) {

//            if (view == signInButton){
//                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
//                startActivity(intent);
//            }
//            if (view == signUpButton){
//                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
//                startActivity(intent);
//            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
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
        navDrawAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(navDrawAdapter);
        mDrawerList.setOnItemClickListener(new BaseActivity.DrawerItemClickListener());
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
        return super.onOptionsItemSelected(item);
    }

}
