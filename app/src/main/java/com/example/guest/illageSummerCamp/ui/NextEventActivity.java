package com.example.guest.illageSummerCamp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.models.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NextEventActivity extends AppCompatActivity {
    @Bind(R.id.nextEventTitle) TextView mNextEventTitle;
    @Bind(R.id.nextEventDescription) TextView mNextEventDescription;
    @Bind(R.id.nextEventDate) TextView mNextEventDate;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_event);
        ButterKnife.bind(this);
        Event nextEvent = Event.findRecent();

        mNextEventTitle.setText(nextEvent.getEventTitle());
        mNextEventDescription.setText(nextEvent.getEventDescription());
        mNextEventDate.setText(nextEvent.getDateTime().toString());
    }


}
