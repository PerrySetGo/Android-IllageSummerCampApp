package com.example.guest.shotsnap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mViewAllButton;
    private Button mHedgehogButton;
    private Button mFoxButton;

    private CategoryLib mCategoryLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface vit = Typeface.createFromAsset(getAssets(),"fonts/vitreous_medium.ttf");
        TextView titleLabel = (TextView)findViewById(R.id.titleLabel);
        titleLabel.setTypeface(vit);

        mViewAllButton = (Button) findViewById(R.id.allActivitiesButton);
        mHedgehogButton = (Button) findViewById(R.id.hedgeButton);
        mFoxButton = (Button) findViewById(R.id.foxButton);

        mCategoryLib = new CategoryLib();

        mViewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowPhotosActivity.class);
                intent.putExtra("category", mCategoryLib.getCategories().get(0));
                startActivity(intent);
            }
        });

        mHedgehogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowPhotosActivity.class);
                intent.putExtra("category", mCategoryLib.getCategories().get(1));
                startActivity(intent);
            }
        });

        mFoxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowPhotosActivity.class);
                intent.putExtra("category", mCategoryLib.getCategories().get(2));
                startActivity(intent);
            }
        });
    }

}
