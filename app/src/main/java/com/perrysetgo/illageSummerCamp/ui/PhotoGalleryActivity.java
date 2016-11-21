package com.perrysetgo.illageSummerCamp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.perrysetgo.illageSummerCamp.Constants;
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

    ValueEventListener mPhotosListener;
    DatabaseReference mPhotosReference;
    ArrayList<Photo> photos = new ArrayList<Photo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        ButterKnife.bind(this);

        mPhotosReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_PHOTOS);

        mPhotosListener = mPhotosReference.addValueEventListener(new ValueEventListener() {

                                                                     @Override
                                                                     public void onDataChange(DataSnapshot dataSnapshot) {
                                                                         for (DataSnapshot photoSnapshot : dataSnapshot.getChildren()) {
                                                                             photos.add(photoSnapshot.getValue(Photo.class));
                                                                         }
                                                                     }

                                                                     @Override
                                                                     public void onCancelled(DatabaseError databaseError) {

                                                                     }

                                                                 });

        mAdapter = new PhotoGalleryAdapter(photos);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PhotoGalleryActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        }
    }


