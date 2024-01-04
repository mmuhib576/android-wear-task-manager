package com.example.muhibmohammedassignment02.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.muhibmohammedassignment02.R;

import android.content.Intent;
import android.app.Activity;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.muhibmohammedassignment02.model.TaskModel;
import com.example.muhibmohammedassignment02.utils.TaskUtils;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskIdEditText;
    private EditText taskNameEditText;
    private EditText dueDateEditText;
    private EditText dueTimeEditText;
    private static final int SPEECH_REQUEST_CODE = 123;


    private Button btnVoiceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        btnVoiceInput = findViewById(R.id.btnVoiceInput);
        taskNameEditText = findViewById(R.id.editTextTaskName);

        btnVoiceInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
            }
        });

        taskIdEditText = findViewById(R.id.editTextTaskId);
        taskNameEditText = findViewById(R.id.editTextTaskName);
        dueDateEditText = findViewById(R.id.editTextDueDate);
        dueTimeEditText = findViewById(R.id.editTextDueTime);

        Button addButton = findViewById(R.id.btnAddTask);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }

    private void addTask() {
        // Get user input
        int taskId = Integer.parseInt(taskIdEditText.getText().toString());
        String taskName = taskNameEditText.getText().toString();
        long dueDate = Long.parseLong(dueDateEditText.getText().toString());
        long dueTime = Long.parseLong(dueTimeEditText.getText().toString());

        // Create a new TaskModel
        TaskModel task = new TaskModel(taskId, taskName, dueDate, dueTime);

        // Add the task to the persistent storage
        TaskUtils.addTask(this, task);

        // Set the result to RESULT_OK to indicate success
        setResult(RESULT_OK);

        // Finish the activity
        finish();
    }
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");

        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (results != null && !results.isEmpty()) {
                // Get the first result as the task name
                String spokenText = results.get(0);
                taskNameEditText.setText(spokenText);
            }
        }
    }

}