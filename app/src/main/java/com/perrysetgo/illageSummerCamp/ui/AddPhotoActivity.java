package com.perrysetgo.illageSummerCamp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddPhotoActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_FROM_GALLERY = 2;

    @Bind(R.id.addPhotoButton) Button addPhotoButton;
    @Bind(R.id.galleryUploadButton) Button galleryUploadButton;
    @Bind(R.id.addImageLabel) ImageView addImageLabel;

    String TAG = AddPhotoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        ButterKnife.bind(this);
        addPhotoButton.setOnClickListener(this);
        galleryUploadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == addPhotoButton) {
            onLaunchCamera();
        }
        if (view == galleryUploadButton){
            Log.d(TAG, "here");
            accessPhoneGallery();
        }


    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }

    public void accessPhoneGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_FROM_GALLERY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Drawable myDrawable = getResources().getDrawable(R.drawable.no_image);
        Bitmap imageBitmap = ((BitmapDrawable) myDrawable).getBitmap();

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
        }
        //need to manage permissions here, for now set target API to 22 to circumvent
        else if (requestCode == REQUEST_FROM_GALLERY && resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            try {
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                imageBitmap = (Bitmap) BitmapFactory.decodeStream(imageStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Log.d(TAG, "no image");
        }

        encodeBitmapAndSaveToFirebase(imageBitmap);
        addImageLabel.setImageBitmap(imageBitmap);
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_PHOTOS);
//                .child("imageUrl"); change this if nesting is needed.
        ref.push().setValue(imageEncoded);
    }

}
