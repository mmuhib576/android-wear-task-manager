package com.example.muhibmohammedassignment02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.muhibmohammedassignment02.R;
import com.example.muhibmohammedassignment02.model.TaskModel;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    private List<TaskModel> taskList;

    public TaskRecyclerViewAdapter(List<TaskModel> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TaskModel task = taskList.get(position);
        holder.taskIdTextView.setText(String.valueOf(task.getId()));  // Converting int to String
        holder.taskNameTextView.setText(task.getName());
        holder.dueDateTextView.setText(formatDate(task.getDueDate()));
        holder.dueTimeTextView.setText(formatTime(task.getDueTime()));
    }

    // Helper method to format the date
    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    private String formatTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskIdTextView;
        TextView taskNameTextView;
        TextView dueDateTextView;
        TextView dueTimeTextView;


            TaskViewHolder(View itemView) {
                super(itemView);
                taskIdTextView = itemView.findViewById(R.id.taskIdTextView);
                taskNameTextView = itemView.findViewById(R.id.taskNameTextView);
                dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
                dueTimeTextView = itemView.findViewById(R.id.dueTimeTextView);
            }
        }
    }