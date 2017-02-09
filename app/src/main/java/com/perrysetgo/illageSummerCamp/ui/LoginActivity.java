package com.perrysetgo.illageSummerCamp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.nameInput) EditText mNameEdit;
    @Bind(R.id.passwordInput) EditText mPasswordEdit;
    @Bind(R.id.loginButton) Button mLoginButton;

    public static final String TAG = LoginActivity.class.getSimpleName();
    SharedPreferences mSharedPreferences;
    private String mUser;
    private String mPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //prefs
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUser = mSharedPreferences.getString(Constants.PREFERENCES_USER_KEY, null);
        mPw = mSharedPreferences.getString(Constants.PREFERENCES_PW_KEY, null);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usr = mNameEdit.getText().toString();
                String pw = mPasswordEdit.getText().toString();

                if (usr.equals(mUser) && pw.equals(mPw)) {
                    final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    mSharedPreferences.edit().putBoolean(Constants.PREFERENCES_USER_LOGIN_STATUS, true).apply();
                    Toast.makeText(getApplicationContext(), R.string.success_admin_login, Toast.LENGTH_SHORT).show();

                    Thread thread = new Thread() {
                        @Override
                        public void run() { //slight delay to not switch activities while toast still showing.
                            try {
                                Thread.sleep(3000); //CHANGE if change in toast
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    thread.start();
                } else if (usr.equals(mUser) && !pw.equals(mPw)) {
                    Toast.makeText(getApplicationContext(), R.string.incorrect_password, Toast.LENGTH_LONG).show();
                } else if (!usr.equals(mUser) && pw.equals(mPw)) {
                    Toast.makeText(getApplicationContext(), R.string.incorrect_username, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.user_pw_mismatch, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_admin_login:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
