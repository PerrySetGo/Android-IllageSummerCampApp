package com.example.guest.illageSummerCamp.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import com.example.guest.illageSummerCamp.adapters.EventAdapter;
import com.example.guest.illageSummerCamp.models.Event;
import com.example.guest.illageSummerCamp.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class AllEventsActivity extends ListActivity {

    private ArrayList<Event> mEvents;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        mEvents = new ArrayList<>();

        mAdapter = new EventAdapter(this, mEvents);
        setListAdapter(mAdapter);
        refreshEventList();
    }

    private void refreshEventList() {
        setProgressBarIndeterminateVisibility(true);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> eventList, ParseException e) {
                if (e == null) {
                    // If there are results, update the list of events
                    // and notify the adapter
                    setProgressBarIndeterminateVisibility(false);
                    mEvents.clear();
                    for (ParseObject event : eventList) {//rebuild event objects from parse data
                        Event newEvent = new Event(event.getString("title"), event.getString("location"),event.getString("description"),event.getDate("startDateTime"), event.getLong(""));
                        mEvents.add(newEvent);
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }
}
