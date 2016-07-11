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
        mEditor = mSharedPreferences.edit();
        mUser = mSharedPreferences.getString(Constants.PREFERENCES_USER_KEY, null);
        mPw = mSharedPreferences.getString(Constants.PREFERENCES_PW_KEY, null);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mNameEdit.getText().toString();
                String pw = mPasswordEdit.getText().toString();
                if (user.equals(mUser) && pw.equals(mPw)) {
                    Log.i(TAG, "both match");
                    final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    mEditor.putBoolean(Constants.PREFERENCES_LOGIN_STATUS, true).apply();
                    Toast.makeText(getApplicationContext(), "success! you are now logged in.", Toast.LENGTH_SHORT).show();
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000); //CHANGE if change in toast

                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    thread.start();

                }
                else if ( user.equals(mUser) && !pw.equals(mPw))
                {
                    Log.i(TAG, "user match only");
                    Toast.makeText(getApplicationContext(), "incorrect password, try again.", Toast.LENGTH_LONG).show();
                }
                else if ( !user.equals(mUser) && pw.equals(mPw))
                {
                    Log.i(TAG, "pw match only");
                    Toast.makeText(getApplicationContext(), "incorrect username, try again.", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.i(TAG, "neither match");
                    Toast.makeText(getApplicationContext(), "incorrect credentials, try again.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
