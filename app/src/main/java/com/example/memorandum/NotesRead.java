package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotesRead extends AppCompatActivity {

    //Variables
    TextView title;
    TextView content;
    Button noteBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_read);

        //Hooks
        title = findViewById(R.id.noteReadTitle);
        content = findViewById(R.id.noteReadContent);
        noteBack = findViewById(R.id.noteBack);

        //Set Text
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));

        // Back Button
        noteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(NotesRead.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
