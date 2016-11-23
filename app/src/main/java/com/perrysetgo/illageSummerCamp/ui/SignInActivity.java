package com.perrysetgo.illageSummerCamp.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.perrysetgo.illageSummerCamp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.passwordLoginButton) Button passwordLoginButton;
    @Bind(R.id.emailEditText) EditText emailEditText;
    @Bind(R.id.passwordEditText) EditText passwordEditText;
    @Bind(R.id.registerTextView) TextView registerTextView;
    @Bind(R.id.forgotPasswordTextView) TextView forgotPassWordTextView;
    public static final String TAG = SignInActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        passwordLoginButton.setOnClickListener(this);
        registerTextView.setOnClickListener(this);
        forgotPassWordTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        Log.d(TAG, "email in onClick");
        if (v == passwordLoginButton) {
            loginWithPassword(email, password);
        }
        if (v == registerTextView){
            Intent intent = new Intent (SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == forgotPassWordTextView){
            mAuth.sendPasswordResetEmail(email) //need user here
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                                new AlertDialog.Builder(SignInActivity.this)
                                        .setTitle("Password Reset Request")
                                        .setMessage("A email was sent to you allowing you to reset your password! See you soon.")
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // nothing happens here.
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                            else {
                                Log.d(TAG, "something broke"); //email not ound??
                            }
                        }
                    });

        }
    }

    private void loginWithPassword(String email, String password) {

        Log.d(TAG, email);

        if (email.equals("")) {
            emailEditText.setError(getString(R.string.enter_email));
            return;
        }
        if (password.equals("")) {
            passwordEditText.setError(getString(R.string.blank_password));
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
