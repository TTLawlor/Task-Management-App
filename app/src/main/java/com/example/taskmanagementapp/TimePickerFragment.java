package com.example.taskmanagementapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private EditText dateEdt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dateEdt = getActivity().findViewById(R.id.editTextDate);

        // Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time the user picks.
        String hour = Integer.toString(hourOfDay);
        String min = Integer.toString(minute);

        dateEdt.setText(dateEdt.getText() + "   " + hour + ":" + min);





    }
}