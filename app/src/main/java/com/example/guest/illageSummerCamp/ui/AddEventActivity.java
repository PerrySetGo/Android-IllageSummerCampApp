package com.example.guest.illageSummerCamp.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.adapters.EventAdapter;
import com.example.guest.illageSummerCamp.models.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddEventActivity extends AppCompatActivity {

    public static final String TAG = AddEventActivity.class.getSimpleName();

    @Bind(R.id.editTitle) EditText mEventTitle;
    @Bind(R.id.editDescription) EditText mEventDescription;
    @Bind(R.id.editLocation) EditText mEventLocation;
    @Bind(R.id.editDate) EditText mEventDate;
    @Bind(R.id.editStartTime) EditText mEventStartTime;
    @Bind(R.id.editEndTime) EditText mEventEndTime;
    @Bind(R.id.newEventButton) Button mNewEventButton;
    @Bind(R.id.noNewButton) Button mNoNewEventButton;
    @Bind(R.id.eventSubmitButton)  Button mSubmitButton;
    private ArrayList<Event> mEvents;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        mEvents = (ArrayList) Event.all();
        mAdapter = new EventAdapter(this, mEvents);
        final TextView startTimeLabel = (TextView) findViewById(R.id.startTimeLabel);
        final TextView endTimeLabel = (TextView) findViewById(R.id.endTimeLabel);
        final TextView newEventLabel = (TextView) findViewById(R.id.newEventLabel);
        final TextView addNewEventLabel = (TextView) findViewById(R.id.addNewEventLabel);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle = mEventTitle.getText().toString();
                String eventLocation = mEventLocation.getText().toString();
                String eventStart = mEventStartTime.getText().toString();
                Log.d(TAG,eventStart);

                String eventEnd = mEventEndTime.getText().toString();
                String eventDescription = mEventDescription.getText().toString();
                String eventDate = mEventDate.getText().toString();
                Log.d(TAG,eventDate);

                String dateTime = eventDate + " " + eventStart;
                Log.d(TAG, dateTime);
                Date eventDateAsDate = getDateFromString(dateTime);


                Event event = new Event(eventTitle, eventLocation, eventDescription, eventDateAsDate);
                event.save();
                mEvents.add(event);
                mAdapter.notifyDataSetChanged();

                mEventTitle.setVisibility(View.INVISIBLE);
                mEventDescription.setVisibility(View.INVISIBLE);
                mEventStartTime.setVisibility(View.INVISIBLE);
                startTimeLabel.setVisibility(View.INVISIBLE);
                mEventEndTime.setVisibility(View.INVISIBLE);
                endTimeLabel.setVisibility(View.INVISIBLE);
                mEventDate.setVisibility(View.INVISIBLE);
                mEventLocation.setVisibility(View.INVISIBLE);
                mSubmitButton.setVisibility(View.INVISIBLE);

                newEventLabel.setVisibility(View.INVISIBLE);
                addNewEventLabel.setVisibility(View.VISIBLE);
                mNewEventButton.setVisibility(View.VISIBLE);
                mNoNewEventButton.setVisibility(View.VISIBLE);

                mNewEventButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(getIntent());
                    }
                });

                mNoNewEventButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public Date getDateFromString(String date){
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.ENGLISH);
        Date newDate = null;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(),"There was an error, please try again",Toast.LENGTH_LONG).show();
        }
        return newDate;
    }

}
