package com.example.guest.illageSummerCamp.ui;

import android.app.ListActivity;
import android.os.Bundle;
import com.example.guest.illageSummerCamp.models.Event;
import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.adapters.EventAdapter;
import java.util.ArrayList;

public class AllEventsActivity extends ListActivity {

    private ArrayList<Event> mEvents;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        mEvents =(ArrayList) Event.all();
        mAdapter = new EventAdapter(this, mEvents);
        setListAdapter(mAdapter);
    }
}
