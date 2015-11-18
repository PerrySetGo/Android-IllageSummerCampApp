package com.example.guest.illageSummerCamp.ui;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.guest.illageSummerCamp.R;
//import com.example.guest.illageSummerCamp.adapters.EventAdapter;
import com.example.guest.illageSummerCamp.fragments.TimePickerFragment;
import com.example.guest.illageSummerCamp.models.Event;
import com.example.guest.illageSummerCamp.models.LocationLib;
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

//public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    public class AddEventActivity extends AppCompatActivity {
    public static final String TAG = AddEventActivity.class.getSimpleName();

    @Bind(R.id.editTitle) EditText mEventTitle;
//    @Bind(R.id.editDescription) EditText mEventDescription;
    @Bind(R.id.newEventButton) Button mNewEventButton;
    @Bind(R.id.noNewButton) Button mNoNewEventButton;
//    @Bind (R.id.endTimeButton) Button endTimeButton;
    @Bind(R.id.eventSubmitButton)  Button mSubmitButton;
//    @Bind(R.id.locationSpinner) Spinner mLocationSpinner;
//    @Bind(R.id.dateSpinner) Spinner mDateSpinner;
//    @Bind(R.id.showStartTimeButton) Button mShowTimeStartButton;
//    @Bind(R.id.startTimeView) TextView startTimeView;
//    @Bind(R.id.endTimeView) TextView endTimeView;
    private String eventTitle;
    private int pickerHour = 0;
    private int pickerMin = 0;
    private int startPickerHour, startPickerMin, endPickerHour, endPickerMin;
    boolean isSettingStartTime = true;
    private ArrayList<Event> mEvents;
    //private EventAdapter mAdapter;
    private ArrayAdapter<String> locationAdapter;
    private ArrayAdapter<String> datesAdapter;
    private LocationLib mLocationLib;
    public String locationChoice;
    public String dateChoice;
    public String trimmedDateChoice;
    private ArrayList<String> mLocationNames; //this is the names of the locations so we can use them for the spinner
    private ArrayList<String> mDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        //mEvents = (ArrayList) Event.all();
        //mAdapter = new EventAdapter(this, mEvents);
//        mLocationNames = new ArrayList<String>();
//
//        //get list of dates for date spinner
//        mDates = new ArrayList<String>();
//        mDates.add("Thu, 08/25/2016");
//        mDates.add("Fri, 08/26/2016");
//        mDates.add("Sat, 08/27/2016");
//        mDates.add("Sun, 08/28/2016");
//
//        //get the list of locations for the spinner
//        mLocationLib = new LocationLib();
//        for (int i = 0; i < mLocationLib.getLocations().size(); i++) {
//            String locName = mLocationLib.getLocations().get(i).getName();
//            mLocationNames.add(locName);
//        }
//
//        locationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mLocationNames);
//        mLocationSpinner.setAdapter(locationAdapter);
//        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                locationChoice = mLocationSpinner.getSelectedItem().toString();
//            }
//
//            public void onNothingSelected(AdapterView<?> arg0) {
//
//            }
//
//        });

//        datesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mDates);
//        mDateSpinner.setAdapter(datesAdapter);
//        mDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                dateChoice = mDateSpinner.getSelectedItem().toString();
//                trimmedDateChoice = dateChoice.substring(5); //cut
//            }
//
//            public void onNothingSelected(AdapterView<?> arg0) {
//
//            }
//        });

        final TextView newEventLabel = (TextView) findViewById(R.id.newEventLabel);
        final TextView addNewEventLabel = (TextView) findViewById(R.id.addNewEventLabel);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });
    }

//        mSubmitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String eventTitle = mEventTitle.getText().toString();
//                String eventDescription = mEventDescription.getText().toString();
//                String dateTime = trimmedDateChoice + " " + startPickerHour + ":" + startPickerMin;
//                long endTime = createEndTimeInLong(endPickerMin,endPickerHour,trimmedDateChoice);
//
//                //empty fields or vals not set
//                if (eventTitle.length() == 0 || eventDescription.length() == 0 || startPickerHour == 0 || endPickerHour == 0){
//                    Toast.makeText(getApplicationContext()," You need to fill out all fields to be able to save this event", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Date eventDateAsDate = getDateFromString(dateTime);
//                    Event event = new Event(eventTitle, locationChoice, eventDescription, eventDateAsDate, endTime);
//                    event.save();
//                    mEvents.add(event);
//                    mAdapter.notifyDataSetChanged();
//                    mEventTitle.setVisibility(View.INVISIBLE);
//                    mLocationSpinner.setVisibility(View.INVISIBLE);
//                    mSubmitButton.setVisibility(View.INVISIBLE);
//                    newEventLabel.setVisibility(View.INVISIBLE);
//                    mShowTimeStartButton.setVisibility(View.INVISIBLE);
//                    endTimeButton.setVisibility(View.INVISIBLE);
//                    startTimeView.setVisibility(View.INVISIBLE);
//                    endTimeView.setVisibility(View.INVISIBLE);
//                    mEventDescription.setVisibility(View.INVISIBLE);
//                    mDateSpinner.setVisibility(View.INVISIBLE);
//
//
//                    addNewEventLabel.setVisibility(View.VISIBLE);
//                    mNewEventButton.setVisibility(View.VISIBLE);
//                    mNoNewEventButton.setVisibility(View.VISIBLE);
//
//                    mNewEventButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            finish();
//                            startActivity(getIntent());
//                        }
//                    });
//
//                    mNoNewEventButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            }
//        });
//    }

//    public void showTimePickerDialog(View v) {
//        TimePickerFragment newFragment = new TimePickerFragment();
//        newFragment.show(getFragmentManager(), "timePicker");
//    }
//
//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        String amOrPm = "pm";
//        pickerHour = hourOfDay;
//        pickerMin = minute;
//        String minutesString = "";
//        if (hourOfDay == 0){ //translate to 12 hr clock. Little workaround to get both timepicker and am/pm label to show up correctly.
//                    amOrPm = "am";
//                    pickerHour+=12;
//                }
//                if (pickerMin < 10) {
//                    minutesString = "0"; //fix weird bug where only one zero is shown on times ending in :00
//                }
//
//        if (isSettingStartTime){
//            startTimeView.setText("Start time set to: " + pickerHour + " : " + minutesString + pickerMin + " " + amOrPm);
//            startPickerHour = pickerHour;
//            startPickerMin  = pickerMin;
//            isSettingStartTime = false;
//        } else {
//            endTimeView.setText("End time set to: " + pickerHour + " : " + minutesString + pickerMin + " " + amOrPm);
//            endPickerHour = pickerHour;
//            endPickerMin = pickerMin;
//        }
//
//    }

//    public Date getDateFromString(String date){
//        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.ENGLISH);
//        Date newDate = null;
//        try {
//            newDate = format.parse(date);
//        } catch (ParseException e) {
//            Toast.makeText(getApplicationContext(),"There was an error, please try again",Toast.LENGTH_LONG).show();
//        }
//        return newDate;
//    }
//
//    public long createEndTimeInLong (int endPickerMin, int endPickerHour, String trimmedDateChoice){
//        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.ENGLISH);
//        Date endDate = null;
//        try {
//            endDate = format.parse(trimmedDateChoice + " " + endPickerHour + ":" + endPickerMin);
//        } catch (ParseException e) {
//            Toast.makeText(getApplicationContext(),"There was an error, please try again",Toast.LENGTH_LONG).show();
//        }
//        return endDate.getTime();
//    }

    private void saveEvent(){
        eventTitle = mEventTitle.getText().toString();
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        ParseObject event = new ParseObject("Event");
        event.put("title", eventTitle);

        event.saveInBackground(new SaveCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                //setProgressBarIndeterminateVisibility(false);
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not saved", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
