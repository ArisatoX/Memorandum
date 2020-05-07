package com.example.memorandum;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String sql = "create table " + TABLE_NAME + "(id integer primary key autoincrement, title text, date text, is_done boolean);";
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
        contentValues.put(COL_4, "false");
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }

}

