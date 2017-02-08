package com.perrysetgo.illageSummerCamp.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.ui.SignInActivity;
import com.perrysetgo.illageSummerCamp.ui.SignUpActivity;


public class SignUpFragment extends DialogFragment {

    public static final String TAG = SignUpFragment.class.getSimpleName();
    SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_dialog, container, false);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());


        Button signupButton = (Button) rootView.findViewById(R.id.signupButton);
        Button signinButton = (Button) rootView.findViewById(R.id.signinButton);
        Button dismissButton = (Button) rootView.findViewById(R.id.dismissButton);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 7/11/16 carry selected event with you and then add to list.
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
                //// TODO: 7/11/16 take user to sign in. carry selected event with you and then add to list.
            }
        });

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSharedPreferences.edit().putBoolean(Constants.PREFERENCES_SHOW_SIGN_ON_DIALOG, false).apply();
                dismiss();
            }
        });

        getDialog().setTitle("Hi there.");
        return rootView;
    }
}
