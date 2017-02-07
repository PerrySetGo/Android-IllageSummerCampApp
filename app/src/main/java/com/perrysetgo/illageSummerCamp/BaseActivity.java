package com.perrysetgo.illageSummerCamp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

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

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout fullLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private int selectedNavItemId;
    private ListView mDrawerList;
    private ArrayAdapter<String> navDrawAdapter;
    public static final String TAG = BaseActivity.class.getSimpleName();



    /**
     * Helper method that can be used by child classes to
     * specify that they don't want a {@link Toolbar}
     * @return true
     */
    protected boolean useToolbar()
    {
        return true;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        /**
         * This is going to be our actual root layout.
         */
        fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        /**
         * {@link FrameLayout} to inflate the child's view. We could also use a {@link android.view.ViewStub}
         */
        FrameLayout activityContainer = (FrameLayout) fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        /**
         * Note that we don't pass the child's layoutId to the parent,
         * instead we pass it our inflated layout.
         */
        super.setContentView(fullLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        if (useToolbar())
        {
            setSupportActionBar(toolbar);
        }
        else
        {
            toolbar.setVisibility(View.GONE);
        }

        setUpNavView();
    }


    protected void setUpNavView()
    {
        navigationView.setNavigationItemSelectedListener(this);

        if( useDrawerToggle()) { // use the hamburger menu
            mDrawerToggle = new ActionBarDrawerToggle(this, fullLayout, toolbar,
                    R.string.nav_drawer_opened,
                    R.string.nav_drawer_closed);

            fullLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        } else if(useToolbar() && getSupportActionBar() != null) {
            // Use home/back button instead
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(getResources()
                    .getDrawable(R.drawable.abc_ic_ab_back_material));
        }
    }


    /**
     * Helper method to allow child classes to opt-out of having the
     * hamburger menu.
     * @return
     */
    protected boolean useDrawerToggle()
    {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        fullLayout.closeDrawer(GravityCompat.START);
        selectedNavItemId = menuItem.getItemId();

        return onOptionsItemSelected(menuItem);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, id + "");
        switch (id) {
                case 0: {
                    Intent intent = new Intent(getApplicationContext(), AboutCampActivity.class );
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
        return super.onOptionsItemSelected(item);
        }
}
