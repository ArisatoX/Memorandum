package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleDialog extends AppCompatActivity {

    //Variables
    TextView id;

    //Database Variables
    DataHelper notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_dialog);

        //Hooks
        id = findViewById(R.id.scheduleDialogID);

        //Database
        notesDatabase = new DataHelper(this);

        //Set Text
        Intent intent = getIntent();
        id.setText(intent.getStringExtra("id"));

    }
}
