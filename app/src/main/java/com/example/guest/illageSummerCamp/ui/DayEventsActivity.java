package com.example.guest.illageSummerCamp.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.adapters.DevicePageAdapter;

public class DayEventsActivity extends AppCompatActivity {

    ViewPager mViewPager;

    DevicePageAdapter mDevicePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_events);

        mDevicePageAdapter = new DevicePageAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDevicePageAdapter);

    }


}
