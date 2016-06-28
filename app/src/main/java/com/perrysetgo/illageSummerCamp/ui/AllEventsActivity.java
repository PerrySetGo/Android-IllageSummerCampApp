package com.perrysetgo.illageSummerCamp.ui;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.perrysetgo.illageSummerCamp.adapters.EventAdapter;
import com.perrysetgo.illageSummerCamp.models.Event;
import com.perrysetgo.illageSummerCamp.R;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class AllEventsActivity extends ListActivity {

    private ArrayList<Event> mEvents;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        ButterKnife.bind(this);
        mEvents = new ArrayList<>();

        mAdapter = new EventAdapter(this, mEvents);
        setListAdapter(mAdapter);
//        refreshEventList();
    }

//    private void refreshEventList() {
//
//        showLoadingDialog();
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
//        query.fromLocalDatastore();
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//
//            @Override
//            public void done(List<ParseObject> eventList, ParseException e) {
//                dismissLoadingDialog();
//                if (e == null) {
//                    //there is something to show
//                    mEvents.clear();
//                    for (ParseObject event : eventList) {//rebuild event objects from parse data
//                        Event newEvent = new Event(event.getString("title"), event.getString("location"),event.getString("description"),event.getDate("startDateTime"), event.getLong(""));
//                        mEvents.add(newEvent);
//                    }
//                    mAdapter.notifyDataSetChanged();
//                } else {
//
//                    Toast.makeText(getApplicationContext(),"There are no events currently stored in the database. Please check back", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }

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

//    private boolean isRegistered() {
//        ParseUser currentUser = ParseUser.getCurrentUser();
//        if (currentUser == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
