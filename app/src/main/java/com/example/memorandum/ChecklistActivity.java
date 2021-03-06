//package com.example.memorandum;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class ChecklistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//
//    //Variables
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    Toolbar toolbar;
//
//    //Main
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_checklist);
//
//        //Hooks
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        toolbar = findViewById(R.id.toolbar);
//
//        //Toolbar
//        setSupportActionBar(toolbar);
//        setTitle("");
//
//        //Navigation Drawer Menu Toggle
//        navigationView.bringToFront();
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
//
//        navigationView.setNavigationItemSelectedListener(this);
//
//        navigationView.setCheckedItem(R.id.Checklists);
//    }
//
//    //Click back -> close drawer
//    @Override
//    public void onBackPressed() {
//
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else {
//            super.onBackPressed();
//        }
//    }
//
//    //To click menu items
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//        //Item Switch
//        switch (menuItem.getItemId())
//        {
//            case R.id.Notes:
//                Intent notes = new Intent(ChecklistActivity.this, MainActivity.class);
//                startActivity(notes);
//                break;
//            case R.id.Checklists:
//                break;
//            case R.id.Schedule:
//                Intent schedule = new Intent(ChecklistActivity.this, ScheduleActivity.class);
//                startActivity(schedule);
//                break;
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START);
//
//        return true;
//    }
//
//}
//
