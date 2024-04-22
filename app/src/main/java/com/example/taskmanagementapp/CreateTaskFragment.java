package com.example.taskmanagementapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class CreateTaskFragment extends Fragment {
    private EditText edTexTitle;
    private EditText edTexDesc;
    private RadioButton radButPriority;
    private EditText edTexDate;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    private Button saveTask;

    // Requires an empty public constructor
    public CreateTaskFragment(){

    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_create_task,container,false);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initializing the database
        firebaseDatabase = FirebaseDatabase.getInstance("https://taskmanagementapp-94d81-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = firebaseDatabase.getReference("Task");

        // Initializing the edittext and radio button
        edTexTitle = (EditText) getActivity().findViewById(R.id.editTextTextTaskTitle);
        edTexDesc = (EditText) getActivity().findViewById(R.id.editTextTextDescMultiLine);
        RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.radio_group);

        edTexDate = (EditText) getActivity().findViewById(R.id.editTextDate);
        edTexDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        saveTask = (Button) getActivity().findViewById(R.id.saveTask);
        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radButPriority = (RadioButton) getActivity().findViewById(radioGroup.getCheckedRadioButtonId());

                String t = edTexTitle.getText().toString();
                String d = edTexDesc.getText().toString();
                String p = (String) radButPriority.getText();

                String strDate = edTexDate.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Date date = null;
                // try parse String to Date
                try {
                    date = formatter.parse(strDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


                // checking if edittext fields are empty or not.
                if (TextUtils.isEmpty(t) && TextUtils.isEmpty(d) && TextUtils.isEmpty(p) && TextUtils.isEmpty(strDate)) {
//                    Toast.makeText(MainActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    addDatatoFirebase(t, d, p, date);
                }
                Task task = new Task(t, d, p, date, null);
            }

            private void addDatatoFirebase(String t, String d, String p, Date date) {
                Task task = new Task(t, d, p, date, null);
                mDatabase.push().setValue(task);
            }
        });
    }
}
