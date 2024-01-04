
package com.example.muhibmohammedassignment02.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.muhibmohammedassignment02.model.TaskModel;
import java.util.HashMap;
import java.util.Map;

public class TaskUtils {
    private static final String TASK_PREFS = "TaskPrefs";
    private static final String TASK_MAP_KEY = "TaskMap";

    public static void addTask(Context context, TaskModel task) {
        Map<String, TaskModel> taskMap = getTaskMap(context);
        taskMap.put(String.valueOf(task.getId()), task);

        SharedPreferences.Editor editor = context.getSharedPreferences(TASK_PREFS, Context.MODE_PRIVATE).edit();
        editor.putString(TASK_MAP_KEY, MapToString(taskMap));
        editor.apply();
    }

    public static Map<String, TaskModel> getTaskMap(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(TASK_PREFS, Context.MODE_PRIVATE);
        String taskMapString = prefs.getString(TASK_MAP_KEY, "");

        if (taskMapString.isEmpty()) {
            return new HashMap<>();
        } else {
            return StringToMap(taskMapString);
        }
    }

    private static String MapToString(Map<String, TaskModel> taskMap) {
        // Converting the Map to a String representation
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, TaskModel> entry : taskMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(",").append(entry.getValue().getId()).append(",");
            stringBuilder.append(entry.getValue().getName()).append(",").append(entry.getValue().getDueDate()).append(",");
            stringBuilder.append(entry.getValue().getDueTime()).append(";");
        }
        return stringBuilder.toString();
    }

    private static Map<String, TaskModel> StringToMap(String taskMapString) {
        Map<String, TaskModel> taskMap = new HashMap<>();
        String[] entries = taskMapString.split(";");
        for (String entry : entries) {
            String[] values = entry.split(",");
            if (values.length == 5) {
                try {
                    int id = Integer.parseInt(values[1]);
                    String name = values[2];
                    long dueDate = Long.parseLong(values[3]);
                    long dueTime = Long.parseLong(values[4]);

                    TaskModel task = new TaskModel(id, name, dueDate, dueTime);
                    taskMap.put(String.valueOf(id), task);
                } catch (NumberFormatException e) {
                    // Handle parsing errors as needed
                    e.printStackTrace();
                }
            }
        }
        return taskMap;
    }
}
