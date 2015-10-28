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

public class RegisterActivity extends AppCompatActivity {

    private EditText mNameEdit;
    private Button mLoginButton;
    private EditText mPasswordEdit;
    private EditText mPasswordVerify;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNameEdit = (EditText) findViewById(R.id.nameText);
        mPasswordEdit = (EditText)findViewById(R.id.editPassword);
        mPasswordVerify = (EditText)findViewById(R.id.editPasswordVerify);
        mLoginButton = (Button) findViewById(R.id.registerButton);
        mPreferences = getApplicationContext().getSharedPreferences("illageCamp", Context.MODE_PRIVATE);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
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