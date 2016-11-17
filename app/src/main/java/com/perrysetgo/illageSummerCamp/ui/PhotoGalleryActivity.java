package com.perrysetgo.illageSummerCamp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.adapters.PhotoGalleryAdapter;
import com.perrysetgo.illageSummerCamp.models.Photo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoGalleryActivity extends AppCompatActivity {

    public static final String TAG = PhotoGalleryActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    PhotoGalleryAdapter mAdapter;

    public ArrayList<Photo> mPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        ButterKnife.bind(this);

        Photo photo = new Photo("Test", "Test", "Test");
        mPhotos.add(photo);

        Photo photoTwo = new Photo("Uri2", "Author2", "Caption2");
        mPhotos.add(photoTwo);


        mAdapter = new PhotoGalleryAdapter(getApplicationContext(), mPhotos);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PhotoGalleryActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

}
