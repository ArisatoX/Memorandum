package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotesRead extends AppCompatActivity {

    //Variables
    TextView title;
    TextView content;
    Button noteBack;
    Button noteUpdate;
    Button noteDelete;
    Button notesPin;
    String id;
    String pinned;
    int zero = 0;
    int one = 1;

    //Database Variables
    DataHelper notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_read);

        //Hooks
        title = findViewById(R.id.noteReadTitle);
        content = findViewById(R.id.noteReadContent);
        noteBack = findViewById(R.id.noteBack);
        noteUpdate = findViewById(R.id.noteUpdate);
        noteDelete = findViewById(R.id.noteDelete);
        notesPin = findViewById(R.id.notesPin);

        //Database
        notesDatabase = new DataHelper(this);

        //Set Text
        Intent intent = getIntent();
        id = getIntent().getStringExtra("id");
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));
        pinned = getIntent().getStringExtra("pinned");
        content.setMovementMethod(new ScrollingMovementMethod());

        // Back Button
        noteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(NotesRead.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Update
        noteUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(NotesRead.this, NotesUpdate.class);
                intent.putExtra("id", id);
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("content", content.getText().toString());
                intent.putExtra("pinned", pinned);
                startActivity(intent);
            }
        });

        //Delete
        noteDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                boolean isDeleted = notesDatabase.deleteNotes(id);
                if (isDeleted== true){
                    Toast.makeText(NotesRead.this, "Your note has been deleted", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(NotesRead.this, "Error Occurred", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(NotesRead.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Pin notes
        notesPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                if (pinned.equals(Integer.toString(zero))){
                    pinned = "1";

                    boolean isUpdated = notesDatabase.updateNotes(id, title.getText().toString(), content.getText().toString(), pinned);
                    if (isUpdated== true){
                        Toast.makeText(NotesRead.this, "Your note has been pinned", Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(NotesRead.this, "Error Occurred", Toast.LENGTH_LONG).show();
                }
                else {
                    pinned = "0";

                    boolean isUpdated = notesDatabase.updateNotes(id, title.getText().toString(), content.getText().toString(), pinned);
                    if (isUpdated== true){
                        Toast.makeText(NotesRead.this, "Your note has been unpinned", Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(NotesRead.this, "Error Occurred", Toast.LENGTH_LONG).show();
                }


                Intent intent = new Intent(NotesRead.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
