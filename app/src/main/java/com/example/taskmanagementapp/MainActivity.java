package com.example.taskmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    TasksFragment tasksFragment = new TasksFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    CreateTaskFragment createTaskFragment = new CreateTaskFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.tasks);

        Utilities.retrieveUserInfo();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tasks:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tasksFragment).commit();
                return true;

            case R.id.calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, calendarFragment).commit();
                return true;

            case R.id.createTask:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, createTaskFragment).commit();
                return true;

        }
                return false;
    }
}