package com.example.memorandum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ScheduleHelper extends SQLiteOpenHelper {

    //Variables
    private static final String DATABASE_NAME = "Schedule.db";
    private static final String TABLE_NAME = "Schedule_table";
    private static final String COL_1 = "id";
    private static final String COL_2 = "title";
    private static final String COL_3 = "date";
    private static final String COL_4 = "is_done";
    private static final int DATABASE_VERSION = 1;


    public ScheduleHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table " + TABLE_NAME + "(id integer primary key autoincrement, title text, date text, is_done integer);";
//        Log.d("Data", "onCreate: "+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Create Note
    public boolean insertData(String title, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, "0");
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<Schedule>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Schedule schedule = new Schedule();
                schedule.setID(Integer.parseInt(cursor.getString(0)));
                schedule.setTitle(cursor.getString(1));
                schedule.setDate(cursor.getString(2));
                schedule.setDone(Integer.parseInt(cursor.getString(3)));

                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String date = cursor.getString(2);
                String done = cursor.getString(3);

                ScheduleActivity.ArrayOfId.add(id);
                ScheduleActivity.ArrayOfTitle.add(title);
                ScheduleActivity.ArrayOfDate.add(date);
                ScheduleActivity.ArrayOfDone.add(done);

                // Adding contact to list
                schedules.add(schedule);
            } while (cursor.moveToNext());
        }

        // return contact list
        return schedules;
    }

    // Deleting single schedule
    public boolean deleteSchedule(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?",
                new String[] { id });
        db.close();

        return true;
    }

    // Change is_done
    public boolean changeDone(String id, int done) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, id);
        values.put(COL_4, done);

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[] { id });

        // update done
        return true;
    }

}

