package com.zybooks.eventapp;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private Button viewBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private Button createBtn;
    private Button backBtn;
    private Button changeBtn;
    private Button sortAscBtn;
    private Button sortDescBtn;
    public static TextView table;
    private EditText titleField;
    private EditText descField;
    private EditText timeField;
    private EditText idField;

    // Layout setup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.add);
        viewBtn = findViewById(R.id.view);
        updateBtn = findViewById(R.id.update);
        deleteBtn = findViewById(R.id.delete);
        createBtn = findViewById(R.id.createButton);
        backBtn = findViewById(R.id.back);
        changeBtn = findViewById(R.id.change);
        sortAscBtn = findViewById(R.id.sortAsc);
        sortDescBtn = findViewById(R.id.sortDesc);

        titleField = findViewById(R.id.titleText);
        descField = findViewById(R.id.descText);
        timeField = findViewById(R.id.timeText);
        idField = findViewById(R.id.idText);

        table = findViewById(R.id.tableContents);

        createBtn.setVisibility(GONE);
        backBtn.setVisibility(GONE);
        changeBtn.setVisibility(GONE);
        sortAscBtn.setVisibility(GONE);
        sortDescBtn.setVisibility(GONE);
        table.setVisibility(GONE);
        titleField.setVisibility(GONE);
        descField.setVisibility(GONE);
        timeField.setVisibility(GONE);
        idField.setVisibility(GONE);
    }

    // Add event method changes button appearances
    public void addEvent(View view) {
        addBtn.setVisibility(GONE);
        viewBtn.setVisibility(GONE);
        updateBtn.setVisibility(GONE);
        deleteBtn.setVisibility(GONE);

        titleField.setVisibility(View.VISIBLE);
        descField.setVisibility(View.VISIBLE);
        timeField.setVisibility(View.VISIBLE);
        createBtn.setVisibility(View.VISIBLE);

        //New button and fields will accept text input for new events
        createBtn.setOnClickListener(v -> {
            String eventTitle = titleField.getText().toString();
            String eventDesc = descField.getText().toString();
            String eventTime = timeField.getText().toString();

            DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

            boolean b = helper.addEventProcess(eventTitle, eventDesc, eventTime);

            if (b) {
                Toast.makeText(MainActivity.this, "Successful addition of event " + eventTitle, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Error when adding event.", Toast.LENGTH_LONG).show();
            }

            // Screen goes back to normal regardless of success or failure
            titleField.setVisibility(GONE);
            descField.setVisibility(GONE);
            timeField.setVisibility(GONE);
            createBtn.setVisibility(GONE);

            addBtn.setVisibility(View.VISIBLE);
            viewBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
        });
    }

    // Displays all events
    public void viewEvents(View view) {
        addBtn.setVisibility(GONE);
        viewBtn.setVisibility(GONE);
        updateBtn.setVisibility(GONE);
        deleteBtn.setVisibility(GONE);
        table.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);
        sortAscBtn.setVisibility(View.VISIBLE);
        sortDescBtn.setVisibility(View.VISIBLE);

        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

        // Default sort, relies on ID number
        ArrayList<String> text = helper.getEvents();
        String result = TextUtils.join(" ", text);
        table.setText(result);

        // Button calls database method to populate it with ascending results
        sortAscBtn.setOnClickListener(v -> {
            ArrayList<String> ascText = helper.sortEventAscending();
            String ascResult = TextUtils.join(" ", ascText);
            table.setText(ascResult);
        });

        // Same as ascending method, only with descending results
        sortDescBtn.setOnClickListener(v -> {
            ArrayList<String> descText = helper.sortEventDescending();
            String descResult = TextUtils.join(" ", descText);
            table.setText(descResult);
        });


        // Button takes user back to main screen
        backBtn.setOnClickListener(v -> {
            addBtn.setVisibility(View.VISIBLE);
            viewBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);

            table.setVisibility(GONE);
            backBtn.setVisibility(GONE);
            sortAscBtn.setVisibility(GONE);
            sortDescBtn.setVisibility(GONE);

        });
    }

    // All three fields can be changed upon update method
    public void updateEvents(View view) {
        addBtn.setVisibility(GONE);
        viewBtn.setVisibility(GONE);
        updateBtn.setVisibility(GONE);
        deleteBtn.setVisibility(GONE);

        changeBtn.setVisibility(View.VISIBLE);
        titleField.setVisibility(View.VISIBLE);
        descField.setVisibility(View.VISIBLE);
        timeField.setVisibility(View.VISIBLE);
        idField.setVisibility(View.VISIBLE);

        changeBtn.setOnClickListener(v -> {
            String eventTitle = titleField.getText().toString();
            String eventDesc = descField.getText().toString();
            String eventTime = timeField.getText().toString();
            String eventId = idField.getText().toString();

            DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

            boolean b = helper.updateEventProcess(eventId, eventTitle, eventDesc, eventTime);
            if(b){
                Toast.makeText(MainActivity.this, "Successfully updated event", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Error updating entry", Toast.LENGTH_LONG).show();
            }
            titleField.setVisibility(GONE);
            descField.setVisibility(GONE);
            timeField.setVisibility(GONE);
            idField.setVisibility(GONE);
            changeBtn.setVisibility(GONE);

            addBtn.setVisibility(View.VISIBLE);
            viewBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
        });
    }

    // Events are deleted by id, meaning user should use view method first to ensure correct usage
    public void deleteEvents(View view) {
        addBtn.setVisibility(GONE);
        viewBtn.setVisibility(GONE);
        updateBtn.setVisibility(GONE);
        deleteBtn.setVisibility(GONE);

        changeBtn.setVisibility(View.VISIBLE);
        idField.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);

        backBtn.setOnClickListener(v -> {
            addBtn.setVisibility(View.VISIBLE);
            viewBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);

            table.setVisibility(GONE);
            backBtn.setVisibility(GONE);
            changeBtn.setVisibility(GONE);
        });

        changeBtn.setOnClickListener(v -> {
            String eventId = idField.getText().toString();

            DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

            boolean b = helper.deleteEventProcess(eventId);
            if (b) {
                Toast.makeText(MainActivity.this, "Successfully deleted event", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Error updating entry", Toast.LENGTH_LONG).show();
            }
            titleField.setVisibility(GONE);
            descField.setVisibility(GONE);
            timeField.setVisibility(GONE);
            idField.setVisibility(GONE);
            changeBtn.setVisibility(GONE);

            addBtn.setVisibility(View.VISIBLE);
            viewBtn.setVisibility(View.VISIBLE);
            updateBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
        });
    }
}