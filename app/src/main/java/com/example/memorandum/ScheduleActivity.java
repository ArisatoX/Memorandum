package com.example.memorandum;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button dateButton;
    TextView dateText;
    Button addSchedule;
    GridView gridView;

    //Database Variables
    ScheduleHelper scheduleDatabase;
    public static ArrayList<String> ArrayOfId = new ArrayList<String>();
    public static ArrayList<String> ArrayOfTitle = new ArrayList<String>();
    public static ArrayList<String> ArrayOfDate = new ArrayList<String>();
    public static ArrayList<String> ArrayOfDone = new ArrayList<String>();

    //Main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        dateButton = findViewById(R.id.dateButton);
        dateText = findViewById(R.id.dateText);
        addSchedule = findViewById(R.id.addSchedule);
        gridView = findViewById(R.id.gridViewSchedule);

        //Database
        scheduleDatabase = new ScheduleHelper(this);

        // Set Date
        Intent intent = getIntent();
        dateText.setText(intent.getStringExtra("date"));

        //Grid View Settings
        final ScheduleActivity.CustomAdapter customAdapter = new ScheduleActivity.CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ScheduleDialog.class);
                intent.putExtra("id", ArrayOfId.get(i));
                intent.putExtra("title", ArrayOfTitle.get(i));
                intent.putExtra("date", ArrayOfDate.get(i));
                intent.putExtra("done", ArrayOfDone.get(i));
                startActivity(intent);
            }
        });

        //List note
        ArrayOfId.clear();
        ArrayOfTitle.clear();
        ArrayOfDate.clear();
        ArrayOfDone.clear();
        String temp = dateText.getText().toString();
//        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
        List<Schedule> schedule = scheduleDatabase.getAllSchedules(temp);
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

        navigationView.setCheckedItem(R.id.Schedule);

        //Calendar Listener Settings
        dateButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick (View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        //Add Schedule
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(ScheduleActivity.this, CreateSchedule.class);
                startActivity(intent);
            }
        });

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
                Intent notes = new Intent(ScheduleActivity.this, MainActivity.class);
                startActivity(notes);
                break;
            case R.id.Checklists:
                Intent checklists = new Intent(ScheduleActivity.this, ChecklistActivity.class);
                startActivity(checklists);
                break;
            case R.id.Schedule:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
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
        dateText.setText(currentDateString);
        Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
        intent.putExtra("date", dateText.getText().toString());
        startActivity(intent);
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

            View view1 = getLayoutInflater().inflate(R.layout.schedule_data,null);
            //getting view in row_data
            TextView title = view1.findViewById(R.id.scheduleDataTitle);
            title.setText(ArrayOfTitle.get(i));

            return view1;

        }
    }





}

