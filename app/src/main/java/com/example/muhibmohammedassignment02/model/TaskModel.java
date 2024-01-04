package com.example.muhibmohammedassignment02.model;

import android.util.Log;

public class TaskModel {
    private int id;
    private String name;
    private long dueDate;
    private long dueTime;

    public TaskModel(int id, String name, long dueDate, long dueTime) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public long getDueTime() {
        return dueTime;
    }

    public void setDueTime(long dueTime) {
        this.dueTime = dueTime;
    }
    // For tasks due in 1 hour
    public boolean isDueWithinOneHour() {
        long currentTime = System.currentTimeMillis();
        long currentTimeMillis = System.currentTimeMillis();
        TaskModel task = new TaskModel(1, "Task 1", currentTimeMillis, currentTimeMillis + 3600000);
        long oneHourInMillis = 60 * 60 * 1000; // One hour in milliseconds
        return dueDate + dueTime - currentTime <= oneHourInMillis;
    }
}