package com.example.guest.illageSummerCamp.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.fragments.DeviceFragment;


public class DevicePageAdapter extends FragmentPagerAdapter {

    String[] devices;
    String[] deviceDescription;

    public DevicePageAdapter(FragmentManager fm, Context context) {
        super(fm);

        Resources resources = context.getResources();

        devices = resources.getStringArray(R.array.devices);
        deviceDescription = resources.getStringArray(R.array.device_description);
    }

    @Override //returns fragment shown on view
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putString(DeviceFragment.DescriptionKey, deviceDescription[position]);

        DeviceFragment deviceFragment = new DeviceFragment();
        deviceFragment.setArguments(bundle);
        return deviceFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return devices[position];
    }

    @Override
    public int getCount() {
        return devices.length;
    }
}
