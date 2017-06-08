package com.perrysetgo.illageSummerCamp.ui;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.adapters.EventAdapter;
import com.perrysetgo.illageSummerCamp.models.Event;
import com.perrysetgo.illageSummerCamp.R;


import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllEventsActivity extends BaseActivity {

    private ArrayList<Event> mEvents;
    public static final String TAG = AllEventsActivity.class.getSimpleName();
    private ProgressDialog progress;
    private EventAdapter mAdapter;
    private ValueEventListener queryRefListener;
    ListView list;

    @Bind (R.id.noEventsBox) TextView noEventsBox;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_EVENTS);
    Query queryRef = ref.orderByValue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        ButterKnife.bind(this);
        noEventsBox.setVisibility(View.INVISIBLE);
        FragmentManager fm = getFragmentManager();
        mEvents = new ArrayList<>();
        list = (ListView) findViewById(R.id.list);
        mAdapter = new EventAdapter(this, mEvents, fm);
        list.setAdapter(mAdapter);

        showLoadingDialog();

        queryRefListener = queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dismissLoadingDialog();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mEvents.add(snapshot.getValue(Event.class));
                }
                sortEventsList(mEvents);
                if (mEvents.size() == 0){

                    noEventsBox.setVisibility(View.VISIBLE);
                }
                dismissLoadingDialog();
                mAdapter.notifyDataSetChanged();
            }
            public void onCancelled(DatabaseError error){
                dismissLoadingDialog();
                Toast.makeText(getApplicationContext(),"There was an issue connecting to the database. Please try again later.", Toast.LENGTH_LONG).show();
                Log.d(TAG, error.toString());
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        queryRef.removeEventListener(queryRefListener);
    }

    public ArrayList<Event> sortEventsList(ArrayList<Event> unsortedEvents){
        Collections.sort(unsortedEvents, Event.sortEvents);
        return unsortedEvents;
    }

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_all_events: return true;
        }
        return super.onOptionsItemSelected(item);
    }
}