package com.perrysetgo.illageSummerCamp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NextEventActivity extends BaseActivity {
    private static final long WINDOW_END = 10800000; //3hrs in ms
    long rightNowInMillis = Calendar.getInstance().getTimeInMillis();
    long eventWindowEnd = rightNowInMillis + WINDOW_END; //3hrs from now.

    @Bind(R.id.nextEventTitleBox) TextView nextEventTitleBox;
    @Bind(R.id.nextEventTitleLabel) TextView nextEventTitleLabel;
    @Bind(R.id.currentTimeLabel) TextView currentTimeLabel;
    @Bind(R.id.nextEventLocationBox) TextView nextEventLocationBox;
    @Bind(R.id.nextEventLocationLabel) TextView nextEventLocationLabel;
    @Bind(R.id.nextEventDateTimeBox) TextView nextEventDateTimeBox;
    @Bind(R.id.nextDateTimeLabel) TextView nextEventDateTimeLabel;
    @Bind(R.id.nextEventDescriptionLabel) TextView nextEventDescriptionLabel;
    @Bind(R.id.nextEventDescriptionBox) TextView nextEventDescriptionBox;
    @Bind(R.id.eventsStatusBox) TextView eventsStatusBox;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat fullDateTime = new SimpleDateFormat("EEE, MM/d yyyy, hh:mm a", Locale.US);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_event);
        ButterKnife.bind(this);
        String currentTime = fullDateTime.format(rightNowInMillis);
        currentTimeLabel.setText("Today is: " + currentTime);
        eventsStatusBox.setVisibility(View.VISIBLE);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_EVENTS);
        Query queryRef = ref.orderByChild("eventStartDateTime");

        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot) {
                SimpleDateFormat tf = new SimpleDateFormat("hh:mm a", Locale.US);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event nextEvent = snapshot.getValue(Event.class);
                    if (nextEvent.getEventStartDateTime() > rightNowInMillis && nextEvent.getEventStartDateTime() < eventWindowEnd) {
                        nextEventTitleBox.setVisibility(View.INVISIBLE);
                        nextEventDateTimeBox.setVisibility(View.INVISIBLE);
                        nextEventDateTimeLabel.setVisibility(View.INVISIBLE);
                        nextEventDescriptionLabel.setVisibility(View.INVISIBLE);
                        nextEventLocationLabel.setVisibility(View.INVISIBLE);
                        nextEventLocationBox.setVisibility(View.INVISIBLE);
                        nextEventTitleLabel.setVisibility(View.INVISIBLE);

                        eventsStatusBox.setText(R.string.no_next_event_text);
                        eventsStatusBox.setVisibility(View.VISIBLE);

                        nextEventDescriptionBox.setVisibility(View.INVISIBLE);
                        continue;
                    } else {
                        nextEventTitleLabel.setVisibility(View.VISIBLE);
                        nextEventTitleBox.setText(nextEvent.getEventTitle());
                        nextEventTitleBox.setVisibility(View.VISIBLE);

                        nextEventLocationLabel.setVisibility(View.VISIBLE);
                        nextEventLocationBox.setText(nextEvent.getEventLocation());
                        nextEventLocationBox.setVisibility(View.VISIBLE);

                        String startDateTime = tf.format(nextEvent.getEventStartDateTime());
                        String endDateTime = tf.format(nextEvent.getEventEndDateTime());
                        nextEventDateTimeBox.setText(startDateTime + " to " + endDateTime);
                        nextEventDateTimeLabel.setVisibility(View.VISIBLE);
                        nextEventDateTimeBox.setVisibility(View.VISIBLE);

                        nextEventDescriptionLabel.setVisibility(View.VISIBLE);
                        nextEventDescriptionBox.setText(nextEvent.getEventDescription());
                        nextEventDescriptionBox.setVisibility(View.VISIBLE);

                        eventsStatusBox.setVisibility(View.INVISIBLE);

                        return;
                    }
                }
            }

            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "There was an issue connecting to the database. Please try again later.", Toast.LENGTH_LONG).show();
                Log.d(TAG, error.toString());
            }
        });

   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_next: return true;
        }

        return super.onOptionsItemSelected(item);
    }
}