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

@Table(name = "events", id = "_id")
public class Event extends Model implements Comparable<Event>{

    public static final String TAG = Event.class.getSimpleName();
    private static final long WINDOW_END = 10800000;

    @Column (name = "EventTitle")
    private String mEventTitle;

    @Column (name = "EventLocation")
    private String mEventLocation;

    @Column(name = "EventDateTimeStart")
    private Date mEventStartDateTime;

//timestamp    @Column(name = "eventDateTimeEnd", index = true)
//    private Date mEventDateTimeEnd;

    @Column (name = "Description")
    private String mEventDescription;

    public Event() {
        super();
    }

    public Event(String eventTitle, String eventLocation,String eventDescription, Date dateTime) {
        mEventTitle = eventTitle;
        mEventLocation = eventLocation;
        mEventDescription = eventDescription;
        mEventStartDateTime = dateTime;
    }

    public void setDateFromString(String date) {
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
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

    public Date getEventStartTime() {
        return mEventStartDateTime;
    }
    public String getEventDescription() {
        return mEventDescription;
    }

    public void setEventDescription(String eventDescription) {
        mEventDescription = eventDescription;
    }

    public Date getDateTime() {
        return mEventStartDateTime;
    }

    public static List<Event> all(){
        return new Select()
                .from(Event.class)
                .orderBy("EventDateTimeStart DESC")
                .execute();
    }

    public static Event findRecent() {
        Calendar rightNow = Calendar.getInstance();
        //get the next event if one starts between now and 3 hours from now
        long eventWindowEnd = rightNow.getTimeInMillis() + WINDOW_END;
        return new Select()
                .from(Event.class)
                .orderBy("EventDateTimeStart DESC")
                .where("EventDateTimeStart > ?", rightNow.getTimeInMillis()) //get current time
                .where("EventDateTimeStart < ?", eventWindowEnd) //add expiration time in MS
                .executeSingle();
    }

    @Override
    public int compareTo(Event o) {
        return getDateTime().compareTo(o.getDateTime());
    }

    public String getEventEndTime() {
        return mEventStartDateTime.toString();
    }


}
