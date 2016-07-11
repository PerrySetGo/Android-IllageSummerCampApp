package com.perrysetgo.illageSummerCamp.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.perrysetgo.illageSummerCamp.R;


public class SignupFragment extends DialogFragment {

    public Button signupButton;
    public Button signinButton;
    public Button dismissButton;
    public static final String TAG = SignupFragment.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_dialog, container, false);

        Button signupButton = (Button) rootView.findViewById(R.id.signupButton);
        Button signinButton = (Button) rootView.findViewById(R.id.signinButton);
        Button dismissButton = (Button) rootView.findViewById(R.id.dismissButton);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "signup");
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "signin");
            }
        });

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "dismiss");
            }
        });

        getDialog().setTitle("Simple Dialog");
        return rootView;
    }
}
