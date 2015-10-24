package com.example.guest.illageSummerCamp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mAboutCampButton;
    private Button mCampMapButton;
    private Button mNextActivityButton;
    private Button mViewAllButton;
    private Button mContactUsButton;
    private TextView mAboutCampText;

//    private CategoryLib mCategoryLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface vit = Typeface.createFromAsset(getAssets(),"fonts/vitreous_medium.ttf");
//        TextView titleLabel = (TextView)findViewById(R.id.titleLabel);
//        titleLabel.setTypeface(vit);

        mAboutCampButton = (Button) findViewById(R.id.aboutCampButton);
        mCampMapButton = (Button) findViewById(R.id.campMapButton);
        mNextActivityButton =(Button) findViewById(R.id.nextActivityButton);
        mViewAllButton = (Button) findViewById(R.id.allActivitiesButton);
        mContactUsButton = (Button) findViewById(R.id.contactUsButton);

        mAboutCampText = (TextView) findViewById(R.id.aboutCampText);


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

        mCampMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IllageMapActivity.class);
                startActivity(intent);
            }
        });
        mNextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //       mCategoryLib = new CategoryLib();

//        mViewAllButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ShowPhotosActivity.class);
//                intent.putExtra("category", mCategoryLib.getCategories().get(0));
//                startActivity(intent);
//            }
//        });
        mContactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
