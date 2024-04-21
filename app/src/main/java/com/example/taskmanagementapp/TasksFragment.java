package com.example.taskmanagementapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {

    RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    private static final String TAG = "Firebase";
    private TaskAdapter allTasksAdapter;
    private List<Task> mTaskList = new ArrayList<>();



    // Requires an empty public constructor
    public TasksFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tasks,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initializing the database
        firebaseDatabase = FirebaseDatabase.getInstance("https://taskmanagementapp-94d81-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = firebaseDatabase.getReference("Task");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //The size calculation is dependent on its children, optimises it a bit more
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                mTaskList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Task user = dataSnapshot1.getValue(Task.class);
                    mTaskList.add(user);
                }
                allTasksAdapter = new TaskAdapter(TasksFragment.this.getContext(), mTaskList);
                recyclerView.setAdapter(allTasksAdapter);
                allTasksAdapter.notifyDataSetChanged();
//                if(mTaskList.isEmpty()){
//
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
