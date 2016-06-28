package com.perrysetgo.illageSummerCamp.ui;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.adapters.EventAdapter;
import com.perrysetgo.illageSummerCamp.fragments.TimePickerFragment;
import com.perrysetgo.illageSummerCamp.models.Event;
import com.perrysetgo.illageSummerCamp.models.LocationLib;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    //public static final String TAG = AddEventActivity.class.getSimpleName();

    @Bind(R.id.editTitle) EditText mEventTitle;
    @Bind(R.id.editDescription) EditText mEventDescription;
    @Bind(R.id.newEventButton) Button mNewEventButton;
    @Bind(R.id.noNewButton) Button mNoNewEventButton;
    @Bind (R.id.endTimeButton) Button endTimeButton;
    @Bind(R.id.showStartTimeButton) Button startTimeButton;
    @Bind(R.id.eventSubmitButton)  Button mSubmitButton;
    @Bind(R.id.locationSpinner) Spinner mLocationSpinner;
    @Bind(R.id.dateSpinner) Spinner mDateSpinner;
    @Bind(R.id.startTimeView) TextView startTimeView;
    @Bind(R.id.endTimeView) TextView endTimeView;
    TimePickerFragment startTimePickerFragment;
    TimePickerFragment endTimePickerFragment;
    int pickerHour = 0;
    int pickerMin = 0;
    int startPickerHour, startPickerMin, endPickerHour, endPickerMin;
    boolean isSettingStartTime = true;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        mAdapter = new EventAdapter(this, mEvents);
        mLocationNames = new ArrayList<>();

        //get list of dates for date spinner
        mDates = new ArrayList<>();
        mDates.add("Thu, 08/25/2016");
        mDates.add("Fri, 08/26/2016");
        mDates.add("Sat, 08/27/2016");
        mDates.add("Sun, 08/28/2016");

        //get the list of locations for the spinner
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

            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        datesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mDates);
        mDateSpinner.setAdapter(datesAdapter);
        mDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                dateChoice = mDateSpinner.getSelectedItem().toString();
                trimmedDateChoice = dateChoice.substring(5); //cut
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        final TextView newEventLabel = (TextView) findViewById(R.id.newEventLabel);
        final TextView addNewEventLabel = (TextView) findViewById(R.id.addNewEventLabel);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEventTitle.length() == 0 || mEventDescription.length() == 0 || startPickerHour == 0 || endPickerHour == 0) {
                    Toast.makeText(getApplicationContext(), " You need to fill out all fields to be able to save this event", Toast.LENGTH_LONG).show();
                } else {
                    String dateTime = trimmedDateChoice + " " + startPickerHour + ":" + startPickerMin;
                    long endTime = createEndTimeInLong(endPickerMin, endPickerHour, trimmedDateChoice);
                    Date eventDateAsDate = getDateFromString(dateTime);
                    saveEvent(eventDateAsDate, endTime);
                    mAdapter.notifyDataSetChanged();
                    mEventTitle.setVisibility(View.INVISIBLE);
                    mLocationSpinner.setVisibility(View.INVISIBLE);
                    mSubmitButton.setVisibility(View.INVISIBLE);
                    newEventLabel.setVisibility(View.INVISIBLE);
                    startTimeButton.setVisibility(View.INVISIBLE);
                    endTimeButton.setVisibility(View.INVISIBLE);
                    startTimeView.setVisibility(View.INVISIBLE);
                    endTimeView.setVisibility(View.INVISIBLE);
                    mEventDescription.setVisibility(View.INVISIBLE);
                    mDateSpinner.setVisibility(View.INVISIBLE);


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
                isSettingStartTime = true;
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





    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String amOrPm = "pm";
        pickerHour = hourOfDay;
        pickerMin = minute;
        String minutesString = "";
        if (hourOfDay == 0){ //translate to 12 hr clock. Little workaround to get both timepicker and am/pm label to show up correctly.
            amOrPm = "am";
            pickerHour+=12;
        }
        else if(hourOfDay > 12){
            amOrPm = "pm";
            pickerHour-=12;
        }
        else if (hourOfDay <12){
            amOrPm = "am";
        }
        if (pickerMin < 10) {
            minutesString = "0"; //fix weird bug where only one zero is shown on times ending in :00
        }

        if (isSettingStartTime){
            startTimeView.setText("Start time set to: " + pickerHour + ":" + minutesString + pickerMin + " " + amOrPm);
            startPickerHour = pickerHour;
            startPickerMin  = pickerMin;
        } else {
            endTimeView.setText("End time set to: " + pickerHour + ":" + minutesString + pickerMin + " " + amOrPm);
            endPickerHour = pickerHour;
            endPickerMin = pickerMin;
        }

    }

    public Date getDateFromString(String date){
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.US);
        Date newDate = null;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(),"There was an error, please try again",Toast.LENGTH_LONG).show();
        }
        return newDate;
    }

    public long createEndTimeInLong (int endPickerMin, int endPickerHour, String trimmedDateChoice){
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.US);
        Date endDate = null;
        try {
            endDate = format.parse(trimmedDateChoice + " " + endPickerHour + ":" + endPickerMin);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(),"There was an error, please try again",Toast.LENGTH_LONG).show();
        }
        return endDate.getTime();
    }

    private void saveEvent(Date eventDateAsDate, long endTime){
        ParseObject event = new ParseObject("Event");
        event.put("title", mEventTitle.getText().toString());
        event.put("location", locationChoice);
        event.put("description", mEventDescription.getText().toString());
        event.put("startDateTime", eventDateAsDate);
        event.put("endTime", endTime);
        event.pinInBackground();

        if (isNetworkAvailable()) {
            event.saveInBackground(new SaveCallback() {
                @Override
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not saved", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else {
            event.saveEventually();
            Toast.makeText(getApplicationContext(), "You are currently offline. Your Event will be saved automatically when you reconnect.", Toast.LENGTH_LONG).show();
        }



    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected() ){
            isAvailable = true;
        }
        return isAvailable;
    }

}
