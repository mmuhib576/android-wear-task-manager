// activity/MainActivity.java
package com.example.muhibmohammedassignment02.activity;

import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muhibmohammedassignment02.R;
import com.example.muhibmohammedassignment02.adapter.TaskRecyclerViewAdapter;
import com.example.muhibmohammedassignment02.model.TaskModel;
import com.example.muhibmohammedassignment02.utils.TaskUtils;
import java.util.ArrayList;
import java.util.Map;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

public class MainActivity extends AppCompatActivity {

    private TaskRecyclerViewAdapter taskAdapter;
    private ArrayList<TaskModel> taskList;

    // Define a constant for the request code
    private static final int ADD_TASK_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        taskAdapter = new TaskRecyclerViewAdapter(taskList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        loadTasks();
        scheduleNotifications();
    }

    private void loadTasks() {
        taskList.clear();
        Map<String, TaskModel> taskMap = TaskUtils.getTaskMap(this);
        for (Map.Entry<String, TaskModel> entry : taskMap.entrySet()) {
            taskList.add(entry.getValue());
        }
        taskAdapter.notifyDataSetChanged();
    }

    private void scheduleNotifications() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        for (TaskModel task : taskList) {
            if (task.isDueWithinOneHour()) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                        .setSmallIcon(R.drawable.ic_notification) // Setting the icon here
                        .setContentTitle("Task is due within one hour")
                        .setContentText("Task ID: " + task.getId() + "\nTask Name: " + task.getName())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);


                Intent resultIntent = new Intent(this, DueTasksActivity.class);
                // Pass any necessary data to DueTasksActivity
                resultIntent.putExtra("taskId", task.getId());
                resultIntent.putExtra("taskName", task.getName());

                // Create the TaskStackBuilder and add the intent, which inflates the back stack
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addNextIntentWithParentStack(resultIntent);

                // Get the PendingIntent containing the entire back stack
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(resultPendingIntent);

                // Trigger the notification
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            "channel_id",
                            "Channel Name",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                }

                // Show the notification
                notificationManager.notify(task.getId(), builder.build());
            }
        }
    }

    public void onAddTaskClick(View view) {
        // Intent to open the AddTaskActivity
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, ADD_TASK_REQUEST_CODE);
    }

    // Override onActivityResult to handle results from the AddTaskActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_OK) {
            // The task was successfully added, update your RecyclerView or perform any other necessary actions.
            loadTasks();
        }
    }
}
