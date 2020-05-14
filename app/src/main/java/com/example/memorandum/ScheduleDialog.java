package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleDialog extends AppCompatActivity {

    //Variables
    String id;
    TextView date;
    TextView title;
    CheckBox done;
    String status;

    //Database Variables
    DataHelper notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_dialog);

        //Hooks
        title = findViewById(R.id.scheduleDialogTitle);
        date = findViewById(R.id.scheduleDialogDate);
        done = findViewById(R.id.scheduleDialogCheckbox);

        //Database
        notesDatabase = new DataHelper(this);

        //Set Text
        Intent intent = getIntent();
        id = getIntent().getStringExtra("id");
        title.setText(intent.getStringExtra("title"));
        date.setText(intent.getStringExtra("date"));
        status = getIntent().getStringExtra("done");
        if (status.equals('1')){
            done.setChecked(true);
        }
        else done.setChecked(false);

    }
}
