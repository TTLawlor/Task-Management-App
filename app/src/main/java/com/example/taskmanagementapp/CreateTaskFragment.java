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

public class CreateTaskFragment extends Fragment {
    private EditText title;
    private EditText desc;
    private RadioButton priority;
    private EditText dateTime;
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
        title = (EditText) getActivity().findViewById(R.id.editTextTextTaskTitle);
        desc = (EditText) getActivity().findViewById(R.id.editTextTextDescMultiLine);
        RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.radio_group);
        dateTime = (EditText) getActivity().findViewById(R.id.editTextDate);

        dateTime = (EditText) getActivity().findViewById(R.id.editTextDate);
        dateTime.setOnClickListener(new View.OnClickListener() {
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

                priority = (RadioButton) getActivity().findViewById(radioGroup.getCheckedRadioButtonId());

                String t = title.getText().toString();
                String d = desc.getText().toString();
                String p = (String) priority.getText();

                // checking if edittext fields are empty or not.
                if (TextUtils.isEmpty(t) && TextUtils.isEmpty(d) && TextUtils.isEmpty(p)) {
//                    Toast.makeText(MainActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    addDatatoFirebase(t, d, p);
                }
                Task task = new Task(t, d, p, null, null);
//                FirebaseAuth auth = FirebaseAuth.getInstance();
//                mDatabase.setValue(task);
            }

            private void addDatatoFirebase(String t, String d, String p) {
                Task task = new Task(t, d, p, null, null);
                mDatabase.push().setValue(task);
            }
        });
    }
}
