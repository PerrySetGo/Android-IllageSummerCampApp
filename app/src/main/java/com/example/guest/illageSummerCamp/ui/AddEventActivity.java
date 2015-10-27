package com.example.guest.illageSummerCamp.ui;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


    private Button mSubmitButton;
    private ArrayList<Event> mEvents;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mEvents =(ArrayList) Event.all();
        mAdapter = new EventAdapter(this, mEvents);
        //setListAdapter(mAdapter);


        mEventTitle = (EditText)findViewById(R.id.editTitle);
        mEventDescription = (EditText)findViewById(R.id.editDescription);
        mEventLocation = (EditText)findViewById(R.id.editLocation);
        mEventDate = (EditText)findViewById(R.id.editDate);
        mEventStartTime = (EditText)findViewById(R.id.editStartTime);
        mEventEndTime = (EditText)findViewById(R.id.editEndTime);


        mSubmitButton = (Button) findViewById(R.id.eventSubmitButton);

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

            }
        });

    }

}
