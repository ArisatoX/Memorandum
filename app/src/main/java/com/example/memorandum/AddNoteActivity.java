package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    //Variables
    EditText notesTitle;
    EditText notesContent;
    Button notesSubmit;

    //Database Variables
    DataHelper notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //Hooks
        notesTitle = findViewById(R.id.notesTitle);
        notesContent = findViewById(R.id.notesContent);
        notesSubmit = findViewById(R.id.notesSubmit);

        //Database
        notesDatabase = new DataHelper(this);

        //Call Function
        addData();
    }

    public void addData() {
        notesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = notesDatabase.insertData(notesTitle.getText().toString(), notesContent.getText().toString());
                if (isInserted == true){
                    Toast.makeText(AddNoteActivity.this, "Your note has been saved", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(AddNoteActivity.this, "Error Occurred", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
