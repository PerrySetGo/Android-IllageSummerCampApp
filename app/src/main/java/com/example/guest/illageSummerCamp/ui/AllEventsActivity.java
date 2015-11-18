package com.example.guest.illageSummerCamp.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.example.guest.illageSummerCamp.models.Event;
import com.example.guest.illageSummerCamp.R;
//import com.example.guest.illageSummerCamp.adapters.EventAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class AllEventsActivity extends ListActivity {

    private List<Event> mEvents;
    //private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        mEvents = new ArrayList<Event>();
        ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, R.layout.event_list_item, mEvents);
        setListAdapter(adapter);
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
                        Event newEvent = new Event(event.getString("title"));
                        mEvents.add(newEvent);
                    }
                    ((ArrayAdapter<Event>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }
}
