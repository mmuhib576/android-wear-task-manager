package com.example.muhibmohammedassignment02.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.muhibmohammedassignment02.R;
import com.example.muhibmohammedassignment02.adapter.TaskRecyclerViewAdapter;
import com.example.muhibmohammedassignment02.model.TaskModel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DueTasksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TaskModel> dueTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_due_tasks);
        long currentTimeMillis = System.currentTimeMillis();
        long oneHourInMillis = 3600000; // 1 hour in milliseconds

        TaskModel task1 = new TaskModel(1, "Task 1", currentTimeMillis, currentTimeMillis + oneHourInMillis);
        TaskModel task2 = new TaskModel(2, "Task 2", currentTimeMillis + oneHourInMillis, currentTimeMillis + 2 * oneHourInMillis);
        TaskModel task3 = new TaskModel(3, "Task 3", currentTimeMillis + 2 * oneHourInMillis, currentTimeMillis + 3 * oneHourInMillis);

        // Add tasks to the dueTaskList
        dueTaskList.add(task1);
        dueTaskList.add(task2);
        dueTaskList.add(task3);

        // Initialize and set up the RecyclerView
        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(dueTaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerViewDueTasks);
        dueTaskList = new ArrayList<>();
    }
}
