package com.example.guest.illageSummerCamp.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.adapters.EventAdapter;
import com.example.guest.illageSummerCamp.models.Event;

import java.util.ArrayList;

public class AddEventActivity extends AppCompatActivity {

    private EditText mEventTitle;
    private EditText mEventDescription;
    private EditText mEventLocation;
    private EditText mEventDate;
    private EditText mEventStartTime;
    private EditText mEventEndTime;
    private Button mNewEventButton;
    private Button mNoNewEventButton;
    private Button mSubmitButton;
    private ArrayList<Event> mEvents;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mEvents =(ArrayList) Event.all();
        mAdapter = new EventAdapter(this, mEvents);
        mEventTitle = (EditText)findViewById(R.id.editTitle);
        mEventDescription = (EditText)findViewById(R.id.editDescription);
        mEventLocation = (EditText)findViewById(R.id.editLocation);
        final TextView startTimeLabel = (TextView)findViewById(R.id.startTimeLabel);
        final TextView endTimeLabel = (TextView)findViewById(R.id.endTimeLabel);
        mEventDate = (EditText)findViewById(R.id.editDate);
        mEventStartTime = (EditText)findViewById(R.id.editStartTime);
        mEventEndTime = (EditText)findViewById(R.id.editEndTime);

        mSubmitButton = (Button) findViewById(R.id.eventSubmitButton);

        final TextView newEventLabel = (TextView)findViewById(R.id.newEventLabel);
        mNewEventButton = (Button)findViewById(R.id.newEventButton);
        mNoNewEventButton = (Button)findViewById(R.id.noNewButton);
        final TextView addNewEventLabel = (TextView)findViewById(R.id.addNewEventLabel);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle = mEventTitle.getText().toString();
                String eventLocation = mEventLocation.getText().toString();
                String eventStart = mEventStartTime.getText().toString();
                String eventEnd = mEventEndTime.getText().toString();
                String eventDescription = mEventDescription.getText().toString();
                String eventDate = mEventDate.getText().toString();
                Event event = new Event(eventTitle, eventLocation, eventStart, eventEnd, eventDescription, eventDate);
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

}
