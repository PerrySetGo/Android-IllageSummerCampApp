package com.perrysetgo.illageSummerCamp.models;

/**
 * Created by epicodus_staff on 11/16/16.
 */
public class Photo {

    public static final String TAG = Photo.class.getSimpleName();

    private String photoUri;
    private String photoAuthor;
    private String photoCaption;

    public Photo (String photoUri, String photoAuthor, String photoCaption){

        this.photoUri = photoUri;
        this.photoAuthor = photoAuthor;
        this.photoCaption = photoCaption;

    }

    public String getPhotoUri() {
        return photoUri;
    }
//how to save
    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getPhotoAuthor() {
        return photoAuthor;
    }

    public void setPhotoAuthor(String photoAuthor) {
        this.photoAuthor = photoAuthor;
    }

    public String getPhotoCaption() {
        return photoCaption;
    }

    public void setPhotoCaption(String photoCaption) {
        this.photoCaption = photoCaption;
    }
}
