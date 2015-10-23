package com.example.guest.shotsnap;

import java.io.Serializable;
import java.util.ArrayList;

public class PhotoLib implements Serializable {

    private ArrayList<Photo> mPhotos;

    public PhotoLib() {
        mPhotos = new ArrayList<Photo>();

        mPhotos.add(new Photo(R.drawable.hedge1, "First hedgehog."));
        mPhotos.add(new Photo(R.drawable.hedge2, "Second hedgehog."));
        mPhotos.add(new Photo(R.drawable.hedge3, "Third hedgehog."));
        mPhotos.add(new Photo(R.drawable.fox1, "First fox."));
        mPhotos.add(new Photo(R.drawable.fox2, "Second fox."));
        mPhotos.add(new Photo(R.drawable.fox3, "Third fox."));

    }

    public ArrayList<Photo> getPhotos() {
        return mPhotos;
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
