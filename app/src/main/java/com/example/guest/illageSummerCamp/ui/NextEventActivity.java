package com.example.guest.illageSummerCamp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.models.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class NextEventActivity extends AppCompatActivity {
    private ArrayList<Event> mEvents;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_event);
        //what i need to do is get a list of all events that are in the database
        mEvents =(ArrayList) Event.all();
        //then i need to sort them by date.

    }

    //get the current time

//    public Date getCurrentDateTime() {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        Log.d(TAG, date.toString());
//        return date;
//    }


    //then i need to go through them one by one until I find one where the date/time is later than the current time
    //then I need to output that event to the page.



}
