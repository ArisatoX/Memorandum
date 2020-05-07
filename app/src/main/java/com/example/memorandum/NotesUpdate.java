package com.example.memorandum;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotesUpdate extends AppCompatActivity {

    //Variables
    EditText title;
    EditText content;
    Button submit;
    String id;

    //Database Variables
    DataHelper notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_update);

        //Hooks
        title = findViewById(R.id.notesTitleUpdate);
        content = findViewById(R.id.notesContentUpdate);
        submit = findViewById(R.id.notesSubmitUpdate);

        //Database
        notesDatabase = new DataHelper(this);

        //Set Text
        Intent intent = getIntent();
        id = getIntent().getStringExtra("id");
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));

        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

        updateData();
    }

    public void updateData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdated = notesDatabase.updateNotes(id, title.getText().toString(), content.getText().toString());
                if (isUpdated== true){
                    Toast.makeText(NotesUpdate.this, "Your note has been updated", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(NotesUpdate.this, "Error Occurred", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(NotesUpdate.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }


}
