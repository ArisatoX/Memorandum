package com.example.memorandum;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button dateButton;
    TextView dateText;

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
    }





}

