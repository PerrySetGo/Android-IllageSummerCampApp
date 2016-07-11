package com.perrysetgo.illageSummerCamp.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrysetgo.illageSummerCamp.R;


public class SignupFragment extends DialogFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_dialog, container, false);
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }

}
