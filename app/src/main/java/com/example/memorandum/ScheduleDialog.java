package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleDialog extends AppCompatActivity {

    //Variables
    String id;
    TextView date;
    TextView title;
    CheckBox done;
    String status;
    String change_status;
    boolean isChanged;
    int zero = 0;
    int one = 1;
    Button scheduleDelete;

    //Database Variables
    ScheduleHelper scheduleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_dialog);

        //Hooks
        title = findViewById(R.id.scheduleDialogTitle);
        date = findViewById(R.id.scheduleDialogDate);
        done = findViewById(R.id.scheduleDialogCheckbox);
        scheduleDelete = findViewById(R.id.scheduleDialogDelete);

        //Database
        scheduleDatabase = new ScheduleHelper(this);

        //Set Text
        Intent intent = getIntent();
        id = getIntent().getStringExtra("id");
        title.setText(intent.getStringExtra("title"));
        date.setText(intent.getStringExtra("date"));
        status = getIntent().getStringExtra("done");

//        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

        if (status.equals(Integer.toString(one))){
            done.setChecked(true);
        }
        else done.setChecked(false);

        // Done
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                if (status.equals(Integer.toString(zero))) {
                    int change = 1;
                    change_status = Integer.toString(change);
                    isChanged = scheduleDatabase.changeDone(id, change_status);
                }
                else {
                    int change = 0;
                    change_status = Integer.toString(change);
                    isChanged = scheduleDatabase.changeDone(id, change_status);
                }

                if (isChanged == true){
                    Toast.makeText(getApplicationContext(), "The schedule has been updated", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(ScheduleDialog.this, "Error Occurred", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ScheduleDialog.this, ScheduleActivity.class);
                intent.putExtra("date", date.getText().toString());
                startActivity(intent);

            }
        });

        //Delete
        scheduleDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                boolean isDeleted = scheduleDatabase.deleteSchedule(id);
                if (isDeleted== true){
                    Toast.makeText(ScheduleDialog.this, "Your schedule has been deleted", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(ScheduleDialog.this, "Error Occurred", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ScheduleDialog.this, ScheduleActivity.class);
                intent.putExtra("date", date.getText().toString());
                startActivity(intent);
            }
        });


    }
}
