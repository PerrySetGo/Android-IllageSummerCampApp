package com.perrysetgo.illageSummerCamp.ui;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.Photo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddPhotoActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_FROM_GALLERY = 2;

    public Bitmap bitmap;

    @Bind(R.id.addPhotoButton) Button addPhotoButton;
    @Bind(R.id.galleryUploadButton) Button galleryUploadButton;
    @Bind(R.id.savePhotoButton) Button savePhotoButton;

    @Bind(R.id.addImageLabel) ImageView addImageLabel;

    @Bind(R.id.photoAuthorEditText) EditText photoAuthorEditText;
    @Bind(R.id.photoCaptionEditText) EditText photoCaptionEditText;

//    String TAG = AddPhotoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        ButterKnife.bind(this);
        addPhotoButton.setOnClickListener(this);
        galleryUploadButton.setOnClickListener(this);
        savePhotoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == addPhotoButton) {
            onLaunchCamera();
        }
        if (view == galleryUploadButton){
            accessPhoneGallery();
        }
        if (view == savePhotoButton){
            savePhoto(photoAuthorEditText.getText().toString(), photoCaptionEditText.getText().toString(), bitmap);
        }
    }


    public void savePhoto (String photoAuthor, String photoCaption, Bitmap bitmap){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

        Photo newPhoto = new Photo (photoAuthor, photoCaption, imageEncoded);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_PHOTOS);

        ref.push().setValue(newPhoto);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
        }

        else if (requestCode == REQUEST_FROM_GALLERY && resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            try {
                getOrientation(getApplicationContext(),imageUri);
                bitmap = getCorrectlyOrientedImage(getApplicationContext(), imageUri, 300);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Drawable myDrawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.no_image);
            bitmap = ((BitmapDrawable) myDrawable).getBitmap();
        }
        addImageLabel.setImageBitmap(bitmap);
    }

    //static methods

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

    public static int getOrientation(Context context, Uri photoUri) {

        Cursor cursor = context.getContentResolver().query(photoUri, new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);
        if (cursor == null || cursor.getCount() != 1)
        {
            return 90;  //Assuming it was taken portrait
        }
        cursor.moveToFirst();
        int cursorInt = cursor.getInt(0);
        cursor.close();
        return cursorInt;
    }

    public static Bitmap getCorrectlyOrientedImage(Context context, Uri photoUri, int maxWidth)
            throws IOException {

        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }

        Bitmap bitmap;
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
            bitmap = BitmapFactory.decodeStream(is, null, options);
        } else {
            bitmap = BitmapFactory.decodeStream(is);
        }

        /*
         * if the orientation is not 0 (or -1, which means we don't know), we
         * have to do a rotation.
         */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
        }

        return bitmap;
    }

}
