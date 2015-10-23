package com.example.guest.shotsnap;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{

    private String mName;
    private ArrayList<Photo> mPhotos;

    public Category(String name) {
        mName = name;
        mPhotos = new ArrayList<Photo>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ArrayList<Photo> getPhotos() {
        return mPhotos;
    }

    public void addPhoto(Photo photo) {
        mPhotos.add(photo);
    }

    public Photo nextPhoto(Photo currentPhoto) {
        int index = mPhotos.indexOf(currentPhoto);
        if (index == mPhotos.size()-1) {
            return mPhotos.get(0);
        } else {
            return mPhotos.get(index+1);
        }
    }

    public Photo previousPhoto(Photo currentPhoto) {
        int index = mPhotos.indexOf(currentPhoto);
        if (index == 0) {
            return mPhotos.get(mPhotos.size()-1);
        } else {
            return mPhotos.get(index-1);
        }
    }
}
