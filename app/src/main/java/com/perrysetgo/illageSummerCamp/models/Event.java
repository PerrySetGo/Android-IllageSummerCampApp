package com.perrysetgo.illageSummerCamp.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.adapters.EventAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class Event {

    public static final String TAG = Event.class.getSimpleName();
    private ArrayList<Event> mEvents;
    private EventAdapter mAdapter;
    public String eventTitle;
    public String eventLocation;
    public long eventStartDateTime;
    public long eventEndDateTime;
    public String eventDescription;
    private String pushId;

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


    public Event(){} //req

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public long getEventStartDateTime() {
        return eventStartDateTime;
    }
    public long getEventEndDateTime() {
        return eventEndDateTime;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    @Override
    public String toString() {
        return this.getEventTitle();
    }

}

