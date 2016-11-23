package com.perrysetgo.illageSummerCamp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SignUpActivity.class.getSimpleName();

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Bind(R.id.createUserButton) Button createUserButton;
    @Bind(R.id.nameEditText) EditText nameEditText;
    @Bind(R.id.emailEditText) EditText emailEditText;
    @Bind(R.id.passwordEditText) EditText passwordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText confirmPasswordEditText;
    @Bind(R.id.loginTextView) TextView loginTextView;

    private DatabaseReference newUserReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        newUserReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_USERS);
        createAuthStateListener();

        ButterKnife.bind(this);

        loginTextView.setOnClickListener(this);
        createUserButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, view.toString());

        if (view == loginTextView) {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class); //manage activity stack
            //custom control back button
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == createUserButton) {
            createNewUser();
        } else {
            Log.i(TAG, "broke");
        }

    }

    private void createNewUser() {
        final String name = nameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (password.length() < 6){
            Toast.makeText(SignUpActivity.this, "Passwords must be six or more characters long.", Toast.LENGTH_LONG).show();
        }

        else if (!checkMatchingPw(password, confirmPassword) && !isValidEmail(email)) {
            Toast.makeText(SignUpActivity.this, "Passwords do not match or are blank, and your email isn't valid. Try harder pls.", Toast.LENGTH_LONG).show();
        }
        else if (!isValidEmail(email) && checkMatchingPw(password, confirmPassword)) {
            Toast.makeText(SignUpActivity.this, "Passwords match, but something is wrong with the email you entered. Oopsies.", Toast.LENGTH_LONG).show();
        }
        else if (!checkMatchingPw(password, confirmPassword) && isValidEmail(email)) {
            Toast.makeText(SignUpActivity.this, "Passwords do not match.", Toast.LENGTH_LONG).show();
        }
        else { //all good

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String key = newUserReference.push().getKey();
                                //add the user to a users table too so we can use it to connect it to events.
                                User newUser = new User(name, email, key);
                                DatabaseReference newRef = FirebaseDatabase
                                        .getInstance()
                                        .getReference(Constants.FIREBASE_CHILD_USERS)
                                        .child(key);

                                newRef.setValue(newUser);
                                Log.i(TAG, "success");
                                final Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                Toast.makeText(getApplicationContext(), "Account created. Logging you in...", Toast.LENGTH_SHORT).show();

                                Thread thread = new Thread(){
                                    @Override
                                    public void run() { //slight delay to not switch activities while toast still showing.
                                        try {
                                            Thread.sleep(4000); //CHANGE if change in toast
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                thread.start();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Something strange happened. Maybe this email address is already registered? Try signing in instead.", Toast.LENGTH_LONG).show(); //this may happen because username or email already exists. what to do?
                            }
                        }

                    });
        }
    }

    private void createAuthStateListener() {
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }


    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean checkMatchingPw(String pw, String confirmPw) {
        boolean pwsMatch = false;
        if (pw.equals(confirmPw)) {
            pwsMatch = true;
        }
        return pwsMatch;
    }
}
