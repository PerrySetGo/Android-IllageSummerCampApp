package com.example.guest.illageSummerCamp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.models.User;

public class MainActivity extends AppCompatActivity {

    private Button mAboutCampButton;
    private Button mCampMapButton;
    private Button mNextActivityButton;
    private Button mViewAllButton;
    private Button mContactUsButton;
    private TextView mAboutCampText;
    private SharedPreferences mPreferences;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("illageCamp", Context.MODE_PRIVATE);

        mAboutCampButton = (Button) findViewById(R.id.aboutCampButton);
        mCampMapButton = (Button) findViewById(R.id.campMapButton);
        mNextActivityButton =(Button) findViewById(R.id.nextActivityButton);
        mViewAllButton = (Button) findViewById(R.id.allActivitiesButton);
        mContactUsButton = (Button) findViewById(R.id.contactUsButton);
        mAboutCampText = (TextView) findViewById(R.id.aboutCampText);
        Button adminButton = (Button) findViewById(R.id.adminButton);
        Button logoutButton = (Button) findViewById(R.id.logoutButton);

        final Button addActivityButton = (Button) findViewById(R.id.addEventButton);


        if (isRegistered()) {
            addActivityButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
            adminButton.setVisibility(View.INVISIBLE);
        }


        mAboutCampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_down_main);
                ((ImageView) findViewById(R.id.homeImage)).startAnimation(a);

                //these should be changed to fadeouts.
                mAboutCampButton.setVisibility(View.INVISIBLE);
                mCampMapButton.setVisibility(View.INVISIBLE);
                mNextActivityButton.setVisibility(View.INVISIBLE);
                mViewAllButton.setVisibility(View.INVISIBLE);
                mContactUsButton.setVisibility(View.INVISIBLE);
                mAboutCampText.setVisibility(View.VISIBLE);

            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                startActivity(getIntent());
            }
        });

        mCampMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IllageMapActivity.class);
                startActivity(intent);
            }
        });

        mViewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllEventsActivity.class);
                startActivity(intent);
            }
        });

        mNextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DayEventsActivity.class);
                startActivity(intent);
            }
        });

        mContactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isRegistered() {
        String username = mPreferences.getString("username", null);
        if (username == null) {
            return false;
        } else {
            setUser(username);
            return true;
        }
    }

    private void setUser(String username) {
        User user = User.find(username);
        if (user != null) {
            mUser = user;
        } else {
            mUser = new User(username);
            mUser.save();
        }
        Toast.makeText(this, "Welcome " + mUser.getName(), Toast.LENGTH_LONG).show();
    }

//    public static User find(String username) {
//        return new Select()
//                .from(User.class)
//                .where("Name = ?", username)
//                .executeSingle();
//    }
}
