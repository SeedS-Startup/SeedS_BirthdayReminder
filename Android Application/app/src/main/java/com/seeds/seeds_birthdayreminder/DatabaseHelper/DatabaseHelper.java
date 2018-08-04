package com.seeds.seeds_birthdayreminder.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;

    public static final String DATABASE_NAME = "Event.db";
    public static final String TABLE_NAME = "event_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FullName";
    public static final String COL_3 = "Picture";
    public static final String COL_4 = "RelationToThePublisher";
    public static final String COL_5 = "EmailAddress";
    public static final String COL_6 = "Letter";
    public static final String COL_7 = "Age";
    public static final String COL_8 = "Birthdate";
    public static final String COL_9 = "PhoneNumber";

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FullName TEXT," +
                "Picture TEXT," +
                "RelationToThePublisher TEXT," +
                "EmailAddress TEXT," +
                "Letter TEXT," +
                "Age INTEGER," +
                "Birthdate TEXT," +
                "PhoneNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(BirthdayEvent event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, event.getFullName());
        contentValues.put(COL_3, event.getPicture());
        contentValues.put(COL_4, event.getRelationToThePublisher());
        contentValues.put(COL_5, event.getEmailAddress());
        contentValues.put(COL_6, event.getLetter());
        contentValues.put(COL_7, event.getAge());
        contentValues.put(COL_8, new Gson().toJson(event.getBirthDate()));
        contentValues.put(COL_9, event.getPhoneNumber());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else {
            event.setID((int) result);
            return true;

        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE " + COL_1 + " = '" + id + "'", null);

        if (res.getCount() > 0) {
            res.moveToFirst();
            return res;
        }
        return null;
    }


    public boolean updateData(int id, BirthdayEvent event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, event.getFullName());
        contentValues.put(COL_3, event.getPicture());
        contentValues.put(COL_4, event.getRelationToThePublisher());
        contentValues.put(COL_5, event.getEmailAddress());
        contentValues.put(COL_6, event.getLetter());
        contentValues.put(COL_7, event.getAge());
        contentValues.put(COL_8, new Gson().toJson(event.getBirthDate()));
        contentValues.put(COL_9, event.getPhoneNumber());
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"" + id});
        return true;
    }

    public Integer deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{""+id});
    }

    public List<BirthdayEvent> getEventsIn(Calendar calendar) {
        List<BirthdayEvent> list = new LinkedList<>();
        Cursor cursor = getAllData();
        cursor.moveToFirst();
        do {
            Calendar date = new Gson().fromJson(cursor.getString(7), Calendar.class);
            if (date.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    date.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                BirthdayEvent event = new BirthdayEvent(cursor.getString(1),
                        cursor.getInt(6),
                        cursor.getString(3), cursor.getString(5),
                        date);
                event.setPicture(cursor.getString(2));
                event.setPhoneNumber(cursor.getString(8));
                event.setEmailAddress(cursor.getString(4));
                event.setID(cursor.getInt(0));
                list.add(event);
            }
        } while (cursor.moveToNext());
        return list;
    }

    public boolean existEventIn(Calendar calendar) {
        Cursor cursor = getAllData();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                Calendar calendar1 = new Gson().fromJson(cursor.getString(7), Calendar.class);
                if (calendar1.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                        calendar1.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }

        return false;
    }
}