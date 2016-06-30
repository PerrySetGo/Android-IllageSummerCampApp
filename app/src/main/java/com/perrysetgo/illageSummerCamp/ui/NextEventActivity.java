package com.perrysetgo.illageSummerCamp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NextEventActivity extends AppCompatActivity {
    private ArrayList<Event> mEvents;
    private static final long WINDOW_END = 10800000; //3hrs in ms
    private Event nextEvent;
    long rightNowInMillis = Calendar.getInstance().getTimeInMillis();
    long eventWindowEnd = rightNowInMillis + WINDOW_END; //3hrs from now.
    @Bind(R.id.nextEventTitle)
    TextView mNextEventTitle;
    @Bind(R.id.nextEventDescription)
    TextView mNextEventDescription;
    @Bind(R.id.nextEventDate)
    TextView mNextEventDate;
    @Bind(R.id.nextLocationLabel)
    TextView mNextLocationLabel;
    @Bind(R.id.nextEventLabel)
    TextView mNextEventLabel;
    @Bind(R.id.nextDescriptionLabel)
    TextView mNextDescriptionLabel;
    @Bind(R.id.nextDateTimeLabel)
    TextView mDateTimeLabel;
    @Bind(R.id.nextLabel)
    TextView mNextLabel;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_event);
        ButterKnife.bind(this);

        findUpcoming(); //should return event if it is present in window
        Log.i(TAG, nextEvent.getEventDescription());

        if (nextEvent != null){
            Log.i(TAG, "success!");
            mNextEventTitle.setText(nextEvent.getEventTitle());
            mNextEventDescription.setText(nextEvent.getEventDescription());

            SimpleDateFormat startTimeFormat = new SimpleDateFormat("EEE, MM/dd hh:mm a", Locale.US);
            String startDateTime = startTimeFormat.format(nextEvent.getEventEndDateTime());

            mNextEventDate.setText(startDateTime);


        }
        else{
            mNextEventTitle.setVisibility(View.INVISIBLE);
            mNextEventDate.setVisibility(View.INVISIBLE);
            mDateTimeLabel.setVisibility(View.INVISIBLE);
            mNextDescriptionLabel.setVisibility(View.INVISIBLE);
            mNextLocationLabel.setVisibility(View.INVISIBLE);
            mNextLabel.setVisibility(View.INVISIBLE);

            mNextEventDescription.setText(R.string.no_next_event_text);
        }


   }


    public void findUpcoming() { //instead of doing work here, pull all events and then loop thrugh them to find future event.
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_EVENTS);
        Query queryRef = ref.orderByValue();
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event checkEvent = snapshot.getValue(Event.class);
                    Log.i(TAG, checkEvent.getEventDescription());
                    if (!(checkEvent.getEventStartDateTime() > rightNowInMillis && checkEvent.getEventStartDateTime() < eventWindowEnd)) {
                        Log.i(TAG, "not in window!");
                        nextEvent = null;
                        continue;
                    } else {
                        Log.i(TAG, "in window");
                        nextEvent = checkEvent;
                    }
                }
            }

            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "There was an issue connecting to the database. Please try again later.", Toast.LENGTH_LONG).show();
                Log.d(TAG, error.toString());
            }
        });

    }
}