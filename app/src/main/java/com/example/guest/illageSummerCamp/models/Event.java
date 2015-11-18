package com.example.guest.illageSummerCamp.models;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.util.Calendar;
import java.util.TimeZone;

public class Event extends Model implements Comparable<Event>{

    public static final String TAG = Event.class.getSimpleName();
    private static final long WINDOW_END = 10800000; //3hrs in ms
    private String mEventTitle;
    private String mEventLocation;
    private Date mEventStartDateTime;
    private long mEventEndTime;
    private String mEventDescription;



    public Event(String eventTitle) {
        mEventTitle = eventTitle;
    }

//    public Event(String eventTitle, String eventLocation, String eventDescription, Date dateTime, long endTime) {
//        mEventTitle = eventTitle;
//        mEventLocation = eventLocation;
//        mEventDescription = eventDescription;
//        mEventStartDateTime = dateTime;
//        mEventEndTime = endTime;
//
//    }

    public String getEventDescription() {
        return mEventDescription;
    }

    public Event() {
        super();
    }

    public String getEventLocation() {
        return mEventLocation;
    }

    public void setDateFromString(String date) {
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        sf.setLenient(false);
        try {
            this.mEventStartDateTime = sf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEventTitle() {
        return mEventTitle;
    }

    public Date getDateTime() {
        return mEventStartDateTime;
    }

//    public static List<Event> all(){
//        return new Select()
//                .from(Event.class)
//                .orderBy("EventDateTimeStart ASC")
//                .execute();
//    }
//
//    public static Event findRecent() {
//        Calendar rightNow = Calendar.getInstance();
//        //get the next event if one starts between now and 3 hours from now
//        long eventWindowEnd = rightNow.getTimeInMillis() + WINDOW_END;
//        return new Select()
//                .from(Event.class)
//                .orderBy("EventDateTimeStart DESC")
//                .where("EventDateTimeStart > ?", rightNow.getTimeInMillis()) //get current time
//                .where("EventDateTimeStart < ?", eventWindowEnd) //add expiration time in MS
//                .executeSingle();
//    }

    @Override
    public int compareTo(Event o) {
        return getDateTime().compareTo(o.getDateTime());
    }

    public long getEventEndTime() {
        return mEventEndTime;
    }

    @Override
    public String toString() {
        return this.getEventTitle();
    }


}

