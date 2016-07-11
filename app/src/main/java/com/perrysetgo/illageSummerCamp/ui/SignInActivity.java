package com.perrysetgo.illageSummerCamp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.perrysetgo.illageSummerCamp.R;

import butterknife.Bind;

public class SignInActivity extends AppCompatActivity  {
    @Bind(R.id.registerTextView)
    TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
}
