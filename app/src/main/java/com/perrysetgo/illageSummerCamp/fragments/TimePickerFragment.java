package com.perrysetgo.illageSummerCamp.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    int hour,minute;
    private TimePickerDialog.OnTimeSetListener mListener;

    @Override
    public void onAttach(Context context) { //// TODO: 2/8/17 double check this,.
        super.onAttach(context);
        mListener = (TimePickerDialog.OnTimeSetListener) context;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), mListener, hour, minute, false);
    }
}