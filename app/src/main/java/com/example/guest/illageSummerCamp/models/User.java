package com.example.guest.illageSummerCamp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Users", id = "_id")
public class User extends Model {

    @Column(name = "name")
    private String mName;

    public User() {
        super();
    }

    public User(String name) {
        super();
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public static User find(String username) {
        return new Select()
                .from(User.class)
                .where ("Name = ?", username)
                .executeSingle();
    }
}
