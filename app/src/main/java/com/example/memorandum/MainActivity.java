package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button addNote;
    GridView gridView;

    //Database Variables
    DataHelper notesDatabase;
    public static ArrayList<String> ArrayOfId = new ArrayList<String>();
    public static ArrayList<String> ArrayOfName = new ArrayList<String>();
    public static ArrayList<String> ArrayOfContent = new ArrayList<String>();

    //Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        addNote = findViewById(R.id.addNote);
        gridView = findViewById(R.id.gridView);

        //Database
        notesDatabase = new DataHelper(this);

        //Grid View Settings

        CustomAdapter customAdapter = new CustomAdapter();

        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), NotesRead.class);
                intent.putExtra("id", ArrayOfId.get(i));
                intent.putExtra("title", ArrayOfName.get(i));
                intent.putExtra("content",ArrayOfContent.get(i));
                startActivity(intent);
            }
        });

        //Add note
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        //List note
        ArrayOfId.clear();
        ArrayOfName.clear();
        ArrayOfContent.clear();
        List<Notes> contacts = notesDatabase.getAllNotes();
        customAdapter.notifyDataSetChanged();

        //Toolbar
        setSupportActionBar(toolbar);
        setTitle("");

        //Navigation Drawer Menu Toggle
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.Notes);
    }

    //Click back -> close drawer
    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    //To click menu items
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //Item Switch
        switch (menuItem.getItemId())
        {
            case R.id.Notes:
                break;
            case R.id.Checklists:
                Intent checklists = new Intent(MainActivity.this, ChecklistActivity.class);
                startActivity(checklists);
                break;
            case R.id.Schedule:
                Intent schedule = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(schedule);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    //Grid View Adapter
    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return ArrayOfId.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View view1 = getLayoutInflater().inflate(R.layout.notes_data,null);
            //getting view in row_data
            TextView title = view1.findViewById(R.id.noteDataTitle);
            title.setText(ArrayOfName.get(i));

            return view1;

        }
    }

}

