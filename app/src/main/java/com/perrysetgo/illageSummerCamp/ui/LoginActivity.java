package com.perrysetgo.illageSummerCamp.ui;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
//import com.parse.LogInCallback;
//import com.parse.ParseException;
//import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.nameInput) EditText mNameEdit;
    @Bind(R.id.passwordInput) EditText mPasswordEdit;
    @Bind(R.id.loginButton) Button mLoginButton;

    public static final String TAG = LoginActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mUser;
    private String mPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUser = mSharedPreferences.getString(Constants.PREFERENCES_USER_KEY, null);
        mPw = mSharedPreferences.getString(Constants.PREFERENCES_PW_KEY, null);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNameEdit.equals(mUser) && mPasswordEdit.equals(mPw)) {

                    Log.i(TAG, "both match");
                }
                else if ( mNameEdit.equals(mUser) && !mPasswordEdit.equals(mPw))
                {
                    Log.i(TAG, "user match only");
                }
                else if ( !mNameEdit.equals(mUser) && mPasswordEdit.equals(mPw))
                {
                    Log.i(TAG, "pw match only");
                }
                else {
                    Log.i(TAG, "neither match");
                }


            }
        });
    }
}
