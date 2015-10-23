package com.example.guest.shotsnap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowPhotosActivity extends AppCompatActivity {

    private Button mNextButton;
    private Button mPreviousButton;
    private ImageView mImage;
    private TextView mDescription;

    private Category mCategory;
    private Photo mCurrentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);

        mNextButton = (Button) findViewById(R.id.nextButton);
        mPreviousButton = (Button) findViewById(R.id.previousButton);
        mImage = (ImageView) findViewById(R.id.currentImage);
        mDescription = (TextView) findViewById(R.id.imageDescription);

        mCategory = (Category) getIntent().getSerializableExtra("category");
        mCurrentPhoto = mCategory.getPhotos().get(0);

        setLayoutContent();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentPhoto = mCategory.nextPhoto(mCurrentPhoto);
                setLayoutContent();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentPhoto = mCategory.previousPhoto(mCurrentPhoto);
                setLayoutContent();
            }
        });
    }

    private void setLayoutContent() {
        mImage.setImageResource(mCurrentPhoto.getSrc());
        mDescription.setText(mCurrentPhoto.getDescription());
    }

}
