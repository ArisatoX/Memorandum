package com.example.memorandum;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateSchedule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //Variables
    Button createDateButton;
    EditText createScheduleTitle;
    TextView createDateText;
    Button createSubmit;

    //Database Variables
    ScheduleHelper scheduleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);

        //Hooks
        createDateButton = findViewById(R.id.createDateButton);
        createScheduleTitle = findViewById(R.id.createScheduleTitle);
        createDateText = findViewById(R.id.createDateText);
        createSubmit = findViewById(R.id.createSubmit);

        //Database
        scheduleDatabase = new ScheduleHelper(this);

        //Calendar Listener Settings
        createDateButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick (View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        addSchedule();

    }

    //Set Date Calendar settings
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        createDateText.setText(currentDateString);
    }

    public void addSchedule() {
        createSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = scheduleDatabase.insertData(createScheduleTitle.getText().toString(), createDateText.getText().toString());
                if (isInserted == true){
                    Toast.makeText(CreateSchedule.this, "Your Schedule has been saved", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(CreateSchedule.this, "Error Occurred", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CreateSchedule.this, ScheduleActivity.class);
                intent.putExtra("date", createDateText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
