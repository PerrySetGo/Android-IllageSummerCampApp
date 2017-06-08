package com.perrysetgo.illageSummerCamp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.perrysetgo.illageSummerCamp.ui.AboutCampActivity;
import com.perrysetgo.illageSummerCamp.ui.AddEventActivity;
import com.perrysetgo.illageSummerCamp.ui.AddPhotoActivity;
import com.perrysetgo.illageSummerCamp.ui.AllEventsActivity;
import com.perrysetgo.illageSummerCamp.ui.ContactActivity;
import com.perrysetgo.illageSummerCamp.ui.IllageMapActivity;
import com.perrysetgo.illageSummerCamp.ui.LoginActivity;
import com.perrysetgo.illageSummerCamp.ui.NextEventActivity;
import com.perrysetgo.illageSummerCamp.ui.PhotoGalleryActivity;
import com.perrysetgo.illageSummerCamp.ui.SignInActivity;
import com.perrysetgo.illageSummerCamp.ui.SignUpActivity;

public class BaseActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout fullLayout;
    private Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;
    int selectedNavItemId;
    SharedPreferences mSharedPreferences;
    String TAG = BaseActivity.class.getSimpleName();
    public boolean loggedInAsAdmin;

    protected boolean useToolbar() {
        return true;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loggedInAsAdmin = mSharedPreferences.getBoolean(Constants.PREFERENCES_USER_LOGIN_STATUS, true); //logged in as admin?


        /**
         * {@link FrameLayout} to inflate the child's view. We could also use a {@link android.view.ViewStub}
         */
        FrameLayout activityContainer = (FrameLayout) fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        if (useToolbar()) {
            setSupportActionBar(toolbar);
        } else {
            toolbar.setVisibility(View.GONE);
        }
        setUpNavView();
    }

    protected void setUpNavView() {
        navigationView.setNavigationItemSelectedListener(this);

        if (useDrawerToggle()) { // use the hamburger menu
            mDrawerToggle = new ActionBarDrawerToggle(this, fullLayout, toolbar,
                    R.string.nav_drawer_opened,
                    R.string.nav_drawer_closed);

            fullLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        } else if (useToolbar() && getSupportActionBar() != null) {
            // Use home/back button instead
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_keyboard_arrow_left_black_24dp));
        }
    }

    protected boolean useDrawerToggle() {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fullLayout.closeDrawer(GravityCompat.START);
        selectedNavItemId = menuItem.getItemId();
        return onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(loggedInAsAdmin) {
            Log.d(TAG, "LOGGED IN - SHOW MENU");
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, id + "");
        switch (id) {
            case R.id.action_about: {
                Intent intent = new Intent(getApplicationContext(), AboutCampActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_main: {
                Intent intent = new Intent(getApplicationContext(), AboutCampActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_map: {
                Intent intent = new Intent(getApplicationContext(), IllageMapActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_next: {
                Intent intent = new Intent(getApplicationContext(), NextEventActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_all_events: {
                Intent intent = new Intent(getApplicationContext(), AllEventsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_contact: {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_add_event: {
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_add_photo: {
                Intent intent = new Intent(getApplicationContext(), AddPhotoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_photo_gallery: {
                Intent intent = new Intent(getApplicationContext(), PhotoGalleryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_sign_in: {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_sign_up: {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.action_sign_out: {
                logout();
                startActivity(getIntent());
                break;
            }
            case R.id.action_logout: {
                logAdminOut();
                finish();
                startActivity(getIntent());
                break;
            }

            case R.id.action_create_event: {
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                startActivity(intent);
                break;
            }

            default:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
    }

    private void logAdminOut(){
        mSharedPreferences.edit().putBoolean(Constants.PREFERENCES_USER_LOGIN_STATUS, false).apply();
        Toast.makeText(getApplicationContext(), "Admin Logged Out", Toast.LENGTH_LONG).show();
    }

}
