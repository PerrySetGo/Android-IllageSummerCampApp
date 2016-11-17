package com.perrysetgo.illageSummerCamp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.Photo;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.PhotoGalleryViewHolder> {
    private ArrayList<Photo> mPhotos = new ArrayList<>();
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;


    public PhotoGalleryAdapter(ArrayList<Photo> photos){
        mPhotos = photos;
    }

    @Override
    public PhotoGalleryAdapter.PhotoGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new PhotoGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoGalleryAdapter.PhotoGalleryViewHolder holder, int position) {
        holder.bindPhoto(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class PhotoGalleryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.photoCaptionTextView) TextView photoCaptionTextView;
        @Bind(R.id.photoAuthorTextView) TextView photoAuthorTextView;
        @Bind(R.id.photoImageView) ImageView photoImageView;

        private Context mContext;

        public PhotoGalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPhoto(Photo photo) {

            if (!photo.getPhotoUri().contains("http")) {
                try {
                    Bitmap imageBitmap = decodeFromFirebaseBase64(photo.getPhotoUri());
                    photoImageView.setImageBitmap(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Picasso.with(mContext)
                        .load(photo.getPhotoUri())
                        .resize(MAX_WIDTH, MAX_HEIGHT)
                        .centerCrop()
                        .into(photoImageView);
                photoCaptionTextView.setText(photo.getPhotoCaption());
                photoAuthorTextView.setText(photo.getPhotoAuthor());
            }


            photoCaptionTextView.setText(photo.getPhotoCaption());
            photoAuthorTextView.setText(photo.getPhotoAuthor());
//            Drawable myDrawable = ContextCompat.getDrawable(mContext, R.drawable.no_image);
//            Bitmap imageBitmap = ((BitmapDrawable) myDrawable).getBitmap();
//            photoImageView.setImageBitmap(imageBitmap); //need to make bitmap and set here.
        }
    }
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}
