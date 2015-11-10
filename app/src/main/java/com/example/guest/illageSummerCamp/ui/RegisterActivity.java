package com.example.guest.illageSummerCamp.ui;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.illageSummerCamp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {


    @Bind(R.id.nameText) EditText mNameEdit;
    @Bind(R.id.editPassword) EditText mPasswordEdit;
    @Bind(R.id.editPasswordVerify) EditText mPasswordVerify;
    @Bind(R.id.registerButton) EditText mRegisterButton;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mPreferences = getApplicationContext().getSharedPreferences("illageCamp", Context.MODE_PRIVATE);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                String passwordVerify = mPasswordVerify.getText().toString();

                if (password.equals(passwordVerify)){// ensure accurate detection
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putString("username", name);
                    editor.putString("password", password);
                    editor.commit();
                    Intent intent = new Intent (RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(),"Passwords don't match, please try again", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}