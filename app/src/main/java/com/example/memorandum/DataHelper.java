package com.example.memorandum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    //Variables
    private static final String DATABASE_NAME = "Notes.db";
    private static final String TABLE_NAME = "Notes_table";
    private static final String COL_1 = "id";
    private static final String COL_2 = "title";
    private static final String COL_3 = "content";
    private static final String COL_4 = "pinned";
    private static final String COL_5 = "img";

    private static final int DATABASE_VERSION = 1;
    int one = 1;
    int zero = 0;

    ByteArrayOutputStream objectByteArrayOutputStream;
    byte[] imageInByte;

    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table " + TABLE_NAME + "(id integer primary key autoincrement, title text, content text, pinned text, img blob);";
//        Log.d("Data", "onCreate: "+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Create Note
    public boolean insertData(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, content);
        contentValues.put(COL_4, "0");

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else return true;
    }

    // Read Note
    Notes getNotes(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_1,
                        COL_2, COL_3 }, COL_1 + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Notes note = new Notes(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return note;
    }

    public List<Notes> getAllNotes() {
        List<Notes> notesList = new ArrayList<Notes>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through pinned notes
        if (cursor.moveToFirst()) {
            do {
                Notes note = new Notes();

                note.setID(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setPinned(cursor.getString(3));

                if (cursor.getString(3).equals(Integer.toString(one))) {
                    String id = cursor.getString(0);
                    String title = cursor.getString(1);
                    String content = cursor.getString(2);
                    String pinned = cursor.getString(3);
                    MainActivity.ArrayOfId.add(id);
                    MainActivity.ArrayOfName.add(title);
                    MainActivity.ArrayOfContent.add(content);
                    MainActivity.ArrayOfPinned.add(pinned);

                    // Adding contact to list
                    notesList.add(note);
                } else {

                }
            } while (cursor.moveToNext());

        }

        // looping through unpinned notes
        if (cursor.moveToFirst()) {
            do {
                Notes note = new Notes();

                note.setID(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setPinned(cursor.getString(3));

                if (cursor.getString(3).equals(Integer.toString(zero))) {
                    String id = cursor.getString(0);
                    String title = cursor.getString(1);
                    String content = cursor.getString(2);
                    String pinned = cursor.getString(3);
                    MainActivity.ArrayOfId.add(id);
                    MainActivity.ArrayOfName.add(title);
                    MainActivity.ArrayOfContent.add(content);
                    MainActivity.ArrayOfPinned.add(pinned);

                    // Adding contact to list
                    notesList.add(note);
                } else {

                }
            } while (cursor.moveToNext());

        }

        // return contact list
        return notesList;
    }

    // Updating single note
    public boolean updateNotes(String id, String title, String content, String pinned) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, id);
        values.put(COL_2, title);
        values.put(COL_3, content);
        values.put(COL_4, pinned);

        db.update(TABLE_NAME, values, COL_1 + " = ?", new String[] { id });

        // updating row
        return true;
    }

    // Deleting single notes
    public boolean deleteNotes(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ?",
                new String[] { id });
        db.close();

        return true;
    }

    // Store image
    public boolean storeImage (String id, String title, String content, String pinned, Bitmap imageToStore)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = imageToStore;

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

            imageInByte = objectByteArrayOutputStream.toByteArray();
            ContentValues values = new ContentValues();
            values.put(COL_1, id);
            values.put(COL_2, title);
            values.put(COL_3, content);
            values.put(COL_4, pinned);
            values.put(COL_5, imageInByte);
            db.update(TABLE_NAME, values, COL_1 + " = ?", new String[] { id });

        } catch (Exception e) {

        }

        return true;

    }

}
