package com.perrysetgo.illageSummerCamp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import org.parceler.Parcel;

public class Event {

    //public static final String TAG = Event.class.getSimpleName();
    private static final long WINDOW_END = 10800000; //3hrs in ms
    public String eventTitle;
    public String eventLocation;
    public long eventStartDateTime;
    public long eventEndDateTime;
    public String eventDescription;

    public Event(String eventTitle, String eventLocation, long eventStartDateTime, String eventDescription, long eventEndDateTime) {
        this.eventTitle = eventTitle;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;

    }

    public static Comparator<Event> sortEvents = new Comparator<Event>() {

        @Override
        public int compare(Event e1, Event e2) {
            return (int) (e1.getEventStartDateTime() - e2.getEventEndDateTime());
        }
    };


    public Event(){}; //req

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventTitle() {
        return eventTitle;
    }

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

//    @Override
//    public int compareTo(Event o) {
//        return getDateTime().compareTo(o.getDateTime());
//    }

    public long getEventStartDateTime() {
        return eventStartDateTime;
    }
    public long getEventEndDateTime() {
        return eventEndDateTime;
    }

    @Override
    public String toString() {
        return this.getEventTitle();
    }

}

