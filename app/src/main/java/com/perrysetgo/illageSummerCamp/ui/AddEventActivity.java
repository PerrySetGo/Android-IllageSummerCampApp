package com.perrysetgo.illageSummerCamp.ui;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.adapters.EventAdapter;
import com.perrysetgo.illageSummerCamp.fragments.TimePickerFragment;
import com.perrysetgo.illageSummerCamp.models.Event;
import com.perrysetgo.illageSummerCamp.models.LocationLib;
import com.perrysetgo.illageSummerCamp.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddEventActivity extends BaseActivity implements TimePickerDialog.OnTimeSetListener{

    public static final String TAG = AddEventActivity.class.getSimpleName();

    @Bind(R.id.editTitle) EditText mEventTitle;
    @Bind(R.id.editDescription) EditText mEventDescription;

    @Bind(R.id.dateSpinner) Spinner mDateSpinner;
    @Bind(R.id.eventLocationLabel) TextView mLocationLabel;
    @Bind(R.id.locationSpinner) Spinner mLocationSpinner;

    @Bind(R.id.startTimeButton) Button startTimeButton;
    @Bind(R.id.startTimeView) TextView startTimeView;
    @Bind(R.id.endTimeButton) Button endTimeButton;
    @Bind(R.id.endTimeView) TextView endTimeView;

    @Bind(R.id.eventSubmitButton)  Button mSubmitButton;

    @Bind(R.id.newEventButton) Button mNewEventButton;
    @Bind(R.id.noNewButton) Button mNoNewEventButton;


    //time vars
    TimePickerFragment startTimePickerFragment;
    TimePickerFragment endTimePickerFragment;
    long startDateTimeLong;
    long endDateTimeLong;
    boolean isSettingStartTime = true;

    //event, date, loc
    ArrayList<Event> mEvents;

    EventAdapter mAdapter;
    ArrayAdapter<String> locationAdapter;
    ArrayAdapter<String> datesAdapter;
    LocationLib mLocationLib;
    public String locationChoice;
    public String dateChoice;
    public String trimmedDateChoice;
    ArrayList<String> mLocationNames; //this is the names of the locations so we can use them for the spinner
    ArrayList<String> mDates;

    private DatabaseReference mSavedEventReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        final TextView newEventLabel = (TextView) findViewById(R.id.newEventLabel);
        final TextView addNewEventLabel = (TextView) findViewById(R.id.addNewEventLabel);

        mSavedEventReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_EVENTS);

        mAdapter = new EventAdapter(this, mEvents, getFragmentManager());

        //get list of dates for date spinner - find a diff way to do this.
        mDates = new ArrayList<>();
        mDates.add("Thu, 08/25/2016");
        mDates.add("Fri, 08/26/2016");
        mDates.add("Sat, 08/27/2016");
        mDates.add("Sun, 08/28/2016");


        //start location
        //get the list of locations for the spinner
        mLocationNames = new ArrayList<>();
        mLocationLib = new LocationLib();
        for (int i = 0; i < mLocationLib.getLocations().size(); i++) {
            String locName = mLocationLib.getLocations().get(i).getName();
            mLocationNames.add(locName);
        }

        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mLocationNames);
        mLocationSpinner.setAdapter(locationAdapter);
        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                locationChoice = mLocationSpinner.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {}
        });


        datesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mDates);
        mDateSpinner.setAdapter(datesAdapter);
        mDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                dateChoice = mDateSpinner.getSelectedItem().toString();
                trimmedDateChoice = dateChoice.substring(5); //cut
            }

            public void onNothingSelected(AdapterView<?> arg0) {} //nothing here
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEventTitle.getText().toString().length() == 0 || mEventDescription.getText().toString().length() == 0 ) { //check these for validation.
                    Toast.makeText(getApplicationContext(), "Please fill out all fields to save this event", Toast.LENGTH_LONG).show();
                } else {
                    Event newEvent = new Event(mEventTitle.getText().toString(), locationChoice, startDateTimeLong, mEventDescription.getText().toString(), endDateTimeLong);
                    saveEvent(newEvent);
                    mAdapter.notifyDataSetChanged();

                    mEventTitle.setVisibility(View.INVISIBLE);
                    mDateSpinner.setVisibility(View.INVISIBLE);
                    newEventLabel.setVisibility(View.INVISIBLE);
                    mLocationLabel.setVisibility(View.INVISIBLE);
                    mLocationSpinner.setVisibility(View.INVISIBLE);
                    startTimeButton.setVisibility(View.INVISIBLE);
                    endTimeButton.setVisibility(View.INVISIBLE);
                    startTimeView.setVisibility(View.INVISIBLE);
                    endTimeView.setVisibility(View.INVISIBLE);
                    mEventDescription.setVisibility(View.INVISIBLE);

                    mSubmitButton.setVisibility(View.INVISIBLE);

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
            }
        });


        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSettingStartTime = true; //switch between two pickers
                startTimePickerFragment = new TimePickerFragment();
                startTimePickerFragment.show(getFragmentManager(), "startTimePicker");
            }
        });

        endTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSettingStartTime = false;
                endTimePickerFragment = new TimePickerFragment();
                endTimePickerFragment.show(getFragmentManager(), "endTimePicker");
            }
        });
    }

    public void saveEvent(Event newEvent) {
        String networkMessage;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        String key = mSavedEventReference.push().getKey();

                DatabaseReference faveEventRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_EVENTS)
                .child(key);

        newEvent.setPushId(key);
        faveEventRef.setValue(newEvent);

        if (networkInfo != null && networkInfo.isConnected() ){
            networkMessage = "Your event \"" + newEvent.getEventDescription() + "\" was saved. Yay! ";
        }
        else {
            networkMessage = "It looks like you are currently offline. Your event \"" + newEvent.getEventDescription() + "\" will be saved automatically when you are back online.";
        }
        Toast.makeText(getApplicationContext(), networkMessage, Toast.LENGTH_LONG).show();
    }

    //time translator code
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        setTimeInLong(minute, hourOfDay, trimmedDateChoice); //create the time BEFORE here so it converts correctly in the listview
        String amOrPm = "pm";

        String minutesString = "";
        if (hourOfDay == 0){ //translate to 12 hr clock for display. Little workaround to get both timepicker and am/pm label to show up correctly.
            amOrPm = "am";
            hourOfDay+=12;
        }
        else if(hourOfDay > 12){
            amOrPm = "pm";
            hourOfDay-=12;
        }
        else if (hourOfDay < 12){
            amOrPm = "am";
        }
        if (minute < 10) {
            minutesString = "0"; //fix weird bug where only one zero is shown on times ending in :00
        }

        if (isSettingStartTime){ //global switch
            startTimeView.setText("Start time set to: " + hourOfDay + ":" + minutesString + minute + " " + amOrPm);
        } else {
            endTimeView.setText("End time set to: " + hourOfDay + ":" + minutesString + minute + " " + amOrPm);
        }

    }

    public void setTimeInLong ( int pickerMin, int pickerHour, String trimmedDateChoice){
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.US);
        Date dateAsDate = new Date(); //init to avoid npe lint warning
        try {
            dateAsDate = format.parse(trimmedDateChoice + " " + pickerHour + ":" + pickerMin); //match above
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(),"There was an error, please try again",Toast.LENGTH_LONG).show();
        }

        if (isSettingStartTime){
            startDateTimeLong = dateAsDate.getTime();
            Log.i(TAG, Long.toString(startDateTimeLong));
        }
        else{
            endDateTimeLong = dateAsDate.getTime();
        }

    }
}