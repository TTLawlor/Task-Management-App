package com.example.taskmanagementapp;

import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText dateEdt;

    private Calendar c;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dateEdt = getActivity().findViewById(R.id.editTextDate);

        // Use the current date as the default date in the picker.
        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date the user picks.
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
        Date date = c.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String formattedDate = formatter.format(date);

        dateEdt.setText(formattedDate);

//        DialogFragment timeFragment = new TimePickerFragment();
//        timeFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }
}