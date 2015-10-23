package com.example.guest.shotsnap;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryLib implements Serializable {

    private ArrayList<Category> mCategories;

    public CategoryLib() {
        mCategories = new ArrayList<Category>();

        Category all = new Category("All");
        all.addPhoto(new Photo(R.drawable.hedge1, "First hedgehog."));
        all.addPhoto(new Photo(R.drawable.hedge2, "Second hedgehog."));
        all.addPhoto(new Photo(R.drawable.hedge3, "Third hedgehog."));
        all.addPhoto(new Photo(R.drawable.fox1, "First fox."));
        all.addPhoto(new Photo(R.drawable.fox2, "Second fox."));
        all.addPhoto(new Photo(R.drawable.fox3, "Third fox."));
        mCategories.add(all);

        Category hedgehogs = new Category("Hedgehogs");
        hedgehogs.addPhoto(new Photo(R.drawable.hedge1, "First hedgehog."));
        hedgehogs.addPhoto(new Photo(R.drawable.hedge2, "Second hedgehog."));
        hedgehogs.addPhoto(new Photo(R.drawable.hedge3, "Third hedgehog."));
        mCategories.add(hedgehogs);

        Category foxes = new Category("Foxes");
        foxes.addPhoto(new Photo(R.drawable.fox1, "First fox."));
        foxes.addPhoto(new Photo(R.drawable.fox2, "Second fox."));
        foxes.addPhoto(new Photo(R.drawable.fox3, "Third fox."));
        mCategories.add(foxes);
    }

    public ArrayList<Category> getCategories() {
        return mCategories;
    }
}
