package com.example.guest.illageSummerCamp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Table(name = "events", id = "_id")
public class Event extends Model {

    @Column (name = "EventName")
    private String mEventTitle;

    @Column (name = "EventLocation")
    private String mEventLocation;

    @Column (name = "EventStartTime")
    private String mEventStartTime;

    @Column (name = "EventEndTime")
    private String mEventEndTime;

    @Column (name = "Description")
    private String mEventDescription;

    @Column (name = "EventDate")
    private String mEventDate;

    public Event() {
        super();
    }

    public Event(String eventTitle) {
        mEventTitle = eventTitle;
//        mEventLocation = eventLocation;
//        mEventStartTime = eventStartTime;
//        mEventEndTime = eventEndTime;
//        mEventDescription = eventDescription;
//        mEventDate = eventDate;
    }


    public String getEventTitle() {
        return mEventTitle;
    }

    public void setEventTitle(String eventTitle) {
        mEventTitle = eventTitle;
    }

    public String getEventLocation() {
        return mEventLocation;
    }

    public void setEventLocation(String eventLocation) {
        mEventLocation = eventLocation;
    }

    public String getEventStartTime() {
        return mEventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        mEventStartTime = eventStartTime;
    }

    public String getEventEndTime() {
        return mEventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        mEventEndTime = eventEndTime;
    }

    public String getEventDescription() {
        return mEventDescription;
    }

    public void setEventDescription(String eventDescription) {
        mEventDescription = eventDescription;
    }

    public String getEventDate() {
        return mEventDate;
    }

    public void setEventDate(String eventDate) {
        mEventDate = eventDate;
    }

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d");
        formatter.setTimeZone(TimeZone.getTimeZone("PST"));
        return formatter.format(mEventDate);
    }

    public static List<Event> all(){
        return new Select()
                .from(Event.class)
                .execute();
    }

}
