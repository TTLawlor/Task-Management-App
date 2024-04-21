package com.example.taskmanagementapp;

import java.sql.Time;
import java.util.Date;

public class Task {
    public String title;
    public String description;
    public String priority;
    public Date date;
    public Time time;
    public Boolean completed;

    public Task() {
        //Default constructor required for calls to DataSnapshot.getValue(Task.class)
    }

    public Task(String title, String description, String priority, Date date, Time time) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.time = time;
        completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
