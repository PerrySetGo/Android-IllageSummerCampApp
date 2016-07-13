package com.perrysetgo.illageSummerCamp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.perrysetgo.illageSummerCamp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity  implements View.OnClickListener{
    @Bind(R.id.registerTextView)
    TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
        registerTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
            if (v == registerTextView){
                Intent intent = new Intent (SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
    }
}
