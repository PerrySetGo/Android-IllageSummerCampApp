package com.perrysetgo.illageSummerCamp.ui;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 3;

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
            accessPhoneGallery();
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // We got the permission
//
//            System.out.println("number 6");
//
//        } else {
//
//            System.out.println("number 7");
//
//            // We were not granted permission this time, so don't try to show the contact picker
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//            System.out.println("number 8");
//        }
//    }


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

//        if (ContextCompat.checkSelfPermission(AddPhotoActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(AddPhotoActivity.this,
//                    Manifest.permission.READ_CONTACTS)) {
//
//                // Show an expanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(AddPhotoActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
//                Log.d(TAG, "TWO");
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }



        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
        }

        else if (requestCode == REQUEST_FROM_GALLERY && resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            try {
                getOrientation(getApplicationContext(),imageUri);
                imageBitmap = getCorrectlyOrientedImage(getApplicationContext(), imageUri, 300);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //do nothing - original img placeholder will show.
            Log.d(TAG, "no image");
        }

        encodeBitmapAndSaveToFirebase(imageBitmap);
        addImageLabel.setImageBitmap(imageBitmap);
    }


    public static int getOrientation(Context context, Uri photoUri) {

        Cursor cursor = context.getContentResolver().query(photoUri, new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);
        if (cursor == null || cursor.getCount() != 1)
        {
            return 90;  //Assuming it was taken portrait
        }
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public static Bitmap getCorrectlyOrientedImage(Context context, Uri photoUri, int maxWidth)
            throws IOException {

        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }

        Bitmap srcBitmap;
        is = context.getContentResolver().openInputStream(photoUri);
        if (rotatedWidth > maxWidth || rotatedHeight > maxWidth) {
            float widthRatio = ((float) rotatedWidth) / ((float) maxWidth);
            float heightRatio = ((float) rotatedHeight) / ((float) maxWidth);
            float maxRatio = Math.max(widthRatio, heightRatio);
            Log.d("ImageUtil", String.format("Shrinking. maxRatio=%s",
                    maxRatio));

            // Create the bitmap from file
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) maxRatio;
            srcBitmap = BitmapFactory.decodeStream(is, null, options);
        } else {
            srcBitmap = BitmapFactory.decodeStream(is);
        }
        is.close();

        /*
         * if the orientation is not 0 (or -1, which means we don't know), we
         * have to do a rotation.
         */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(),
                    srcBitmap.getHeight(), matrix, true);
        }

        return srcBitmap;
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
