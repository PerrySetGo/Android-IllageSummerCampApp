package com.example.guest.illageSummerCamp.ui;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

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
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_all_events);

        mEvents = new ArrayList<>();

        mAdapter = new EventAdapter(this, mEvents);
        setListAdapter(mAdapter);
        refreshEventList();
    }

    private void refreshEventList() {

        showLoadingDialog();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> eventList, ParseException e) {
                dismissLoadingDialog();
                if (e == null) {
                    //there is something to show
                    mEvents.clear();
                    for (ParseObject event : eventList) {//rebuild event objects from parse data
                        Event newEvent = new Event(event.getString("title"), event.getString("location"),event.getString("description"),event.getDate("startDateTime"), event.getLong(""));
                        mEvents.add(newEvent);
                    }
                    mAdapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(getApplicationContext(),"There are no events currently stored in the database. Please check back", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private ProgressDialog progress;



    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setMessage("Getting Events");
        }
        progress.show();
    }

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }
}
