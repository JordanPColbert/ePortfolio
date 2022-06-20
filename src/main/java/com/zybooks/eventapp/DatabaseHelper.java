package com.zybooks.eventapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String LOGIN_TABLE = "LOGIN_TABLE";
    public static final String EVENT_TABLE = "EVENT_TABLE";
    public static final String USR_COLUMN = "USERNAME";
    public static final String PSWRD_COLUMN = "PASSWORD";
    public static final String TITLE_COLUMN = "TITLE";
    public static final String DESC_COLUMN = "DESCRIPTION";
    public static final String TIME_COLUMN = "TIME";
    public static final String COL_ID = "_ID";
    public static final String createLogInTable = "CREATE TABLE " + LOGIN_TABLE + " (" + COL_ID + " " +
            "INTEGER PRIMARY KEY AUTOINCREMENT, " + USR_COLUMN + " TEXT, " + PSWRD_COLUMN + " TEXT)";
    public static final String createEventTable = "CREATE TABLE " + EVENT_TABLE + " (" + COL_ID + " " +
            "INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE_COLUMN + " TEXT, " + DESC_COLUMN + " TEXT," +
            " " + TIME_COLUMN + " TEXT)";

    public DatabaseHelper(@Nullable Context context) {

        super(context, "event.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createLogInTable);
        db.execSQL(createEventTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(String usr, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(USR_COLUMN, usr);
        cv.put(PSWRD_COLUMN, pswd);

        long insert = db.insert(LOGIN_TABLE, null, cv);

        return insert != -1;
    }

    public boolean checkLogIn(String usr, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + LOGIN_TABLE + " WHERE " + USR_COLUMN + " = ? AND " + PSWRD_COLUMN + " = ?", new String[] {usr, pswd});

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean addEventProcess(String title, String desc, String time) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TITLE_COLUMN, title);
        cv.put(DESC_COLUMN, desc);
        cv.put(TIME_COLUMN, time);

        long insert = db.insert(EVENT_TABLE, null, cv);

        return insert != -1;
    }

    public ArrayList<String> getEvents() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + EVENT_TABLE, null);
        ArrayList<String> array = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                array.add(COL_ID + ": ");
                array.add(cursor.getString(0));
                array.add(TITLE_COLUMN + ": ");
                array.add(cursor.getString(1));
                array.add(DESC_COLUMN + ": ");
                array.add(cursor.getString(2));
                array.add(TIME_COLUMN + ": ");
                array.add(cursor.getString(3));
                array.add("\n");
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return array;
    }

    public boolean updateEventProcess(String id, String title, String desc, String time) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, id);
        cv.put(TITLE_COLUMN, title);
        cv.put(DESC_COLUMN, desc);
        cv.put(TIME_COLUMN, time);

        long update = db.update(EVENT_TABLE, cv, COL_ID + " = ?", new String[]{id});

        return update != -1;
    }

    // Same as getEvents, only with sorted results (ascending)
    public ArrayList<String> sortEventAscending() {
        MainActivity.table.setText("");
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EVENT_TABLE + " ORDER BY " + TITLE_COLUMN
         + " ASC",null);
        ArrayList<String> array = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                array.add(COL_ID + ": ");
                array.add(cursor.getString(0));
                array.add(TITLE_COLUMN + ": ");
                array.add(cursor.getString(1));
                array.add(DESC_COLUMN + ": ");
                array.add(cursor.getString(2));
                array.add(TIME_COLUMN + ": ");
                array.add(cursor.getString(3));
                array.add("\n");
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return array;
    }

    // Descending results are returned
    public ArrayList<String> sortEventDescending() {
        MainActivity.table.setText("");
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EVENT_TABLE + " ORDER BY " + TITLE_COLUMN
                + " DESC",null);
        ArrayList<String> array = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                array.add(COL_ID + ": ");
                array.add(cursor.getString(0));
                array.add(TITLE_COLUMN + ": ");
                array.add(cursor.getString(1));
                array.add(DESC_COLUMN + ": ");
                array.add(cursor.getString(2));
                array.add(TIME_COLUMN + ": ");
                array.add(cursor.getString(3));
                array.add("\n");
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return array;
    }

    public boolean deleteEventProcess(String id) {
        SQLiteDatabase db = getWritableDatabase();

        long delete = db.delete(EVENT_TABLE, COL_ID + " = ?", new String[]{id});

        return delete != -1;
    }
}
