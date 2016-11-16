package com.perrysetgo.illageSummerCamp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.Photo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by epicodus_staff on 11/16/16.
 */
public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.PhotoGalleryViewHolder> {


    public PhotoGalleryAdapter(Context context, ArrayList photos){
        ArrayList mPhotos = new ArrayList<>();
        Context mContext;

    }

    public class PhotoGalleryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.photoCaptionTextView)
        TextView photoCaptionTextView;
        @Bind(R.id.photoAuthorTextView)
        TextView photoAuthorTextView;
        @Bind(R.id.photoImageView)
        ImageView photoImageView;

        private Context mContext;

        public PhotoGalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        //cannot proceed here until I have created datamodel.

        public void bindRestaurant(Photo photo) {
            photoCaptionTextView.setText(photo.getPhotoCaption());
            photoAuthorTextView.setText(photo.getPhotoAuthor());
            Drawable myDrawable = ContextCompat.getDrawable(mContext, R.drawable.no_image);
            Bitmap imageBitmap = ((BitmapDrawable) myDrawable).getBitmap();
            photoImageView.setImageBitmap(imageBitmap); //need to make bitmap and set here.
        }
    }

}
