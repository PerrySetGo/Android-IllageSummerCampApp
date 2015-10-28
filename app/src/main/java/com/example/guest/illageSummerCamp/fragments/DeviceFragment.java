package com.example.guest.illageSummerCamp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.illageSummerCamp.R;

public class DeviceFragment extends android.support.v4.app.Fragment {

    public static final String DescriptionKey = "descriptionkey";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);

        Bundle bundle = getArguments();

        if (bundle != null){
            String description = bundle.getString(DescriptionKey);

            setValues (view, description);
        }

        return view;
    }

    private void setValues(View view, String description) {
        TextView textView = (TextView)view.findViewById(R.id.tvDeviceDescription);
        textView.setText(description);
    }
}
