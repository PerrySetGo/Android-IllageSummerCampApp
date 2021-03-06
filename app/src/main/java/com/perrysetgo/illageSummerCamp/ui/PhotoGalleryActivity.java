package com.perrysetgo.illageSummerCamp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.adapters.PhotoGalleryAdapter;
import com.perrysetgo.illageSummerCamp.models.Photo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoGalleryActivity extends BaseActivity {

    public static final String TAG = PhotoGalleryActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    PhotoGalleryAdapter mAdapter;
    ProgressDialog progress;

    ValueEventListener mPhotosListener;
    DatabaseReference mPhotosReference;
    ArrayList<Photo> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        handleLoadingDialog();

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

                                                                         mAdapter = new PhotoGalleryAdapter(photos);
                                                                         mRecyclerView.setAdapter(mAdapter);
                                                                         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PhotoGalleryActivity.this);
                                                                         mRecyclerView.setLayoutManager(layoutManager);
                                                                         mRecyclerView.setHasFixedSize(true);

                                                                         handleLoadingDialog();
                                                                     }

                                                                     @Override
                                                                     public void onCancelled(DatabaseError databaseError) {

                                                                     }

                                                                 });

        }

    public void handleLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setMessage("Loading Photos, hang on...");
            progress.show();
        }
        else  {
            progress.dismiss();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_main: return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


