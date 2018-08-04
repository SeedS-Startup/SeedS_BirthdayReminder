package com.seeds.seeds_birthdayreminder.Activity;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.seeds.seeds_birthdayreminder.DatabaseHelper.DatabaseHelper;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.util.Calendar;
import java.util.Locale;

public class AlarmActivity extends AppCompatActivity {
    BirthdayEvent birthdayEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        int id = getIntent().getExtras().getInt("ID");
        Cursor cursor = DatabaseHelper.getInstance(this).getData(id);
        birthdayEvent = new BirthdayEvent(cursor.getInt(0), cursor.getString(1),
                cursor.getInt(6),
                cursor.getString(3), cursor.getString(5),
                new Gson().fromJson(cursor.getString(7), Calendar.class));
        birthdayEvent.setPhoneNumber(cursor.getString(8));
        birthdayEvent.setEmailAddress(cursor.getString(4));
        birthdayEvent.setPicture(cursor.getString(2));

        findViews();
        init();
    }

    private void init() {
        Helper.alarm_age.setText("" + birthdayEvent.getAge());
        Helper.alarm_email.setText(birthdayEvent.getEmailAddress());
        Helper.alarm_name.setText(birthdayEvent.getFullName());
        Helper.alarm_relation.setText(birthdayEvent.getRelationToThePublisher());
        Helper.alarm_letter.setText(birthdayEvent.getLetter());
        Helper.alarm_phone.setText("" + birthdayEvent.getPhoneNumber());
        if (birthdayEvent.getPicture() == null)
            Helper.alarm_photo.setImageResource(R.mipmap.balloon);
        else
            Helper.alarm_photo.setImageBitmap(BitmapFactory.decodeFile(birthdayEvent.getPicture()));
        //Helper.alarm_picture.setImageBitmap(BitmapFactory.decodeFile(birthdayEvent.getPicture()));
        Helper.alarm_date.setText(birthdayEvent.getBirthDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
                + " " + birthdayEvent.getBirthDate().get(Calendar.DAY_OF_MONTH) + "," + birthdayEvent.getBirthDate().get(Calendar.YEAR));

        //find the remained days toward the next birthday
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR) + 1,
                birthdayEvent.getBirthDate().get(Calendar.MONTH),
                birthdayEvent.getBirthDate().get(Calendar.DAY_OF_MONTH));
        long answer = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        int days = (int) (answer / (1000 * 60 * 60 * 24));
        if (days > 365)
            days -= 365;
        //
        Calendar nextTemp = Calendar.getInstance();
        nextTemp.setTimeInMillis((long) (Calendar.getInstance().getTimeInMillis() + days * 24 * 60 * 60 * 1000));

        Helper.alarm_nextBirthday.setText("In " + days + " Days(" + nextTemp.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ")");

        Calendar lastTemp = Calendar.getInstance();
        lastTemp.setTimeInMillis(nextTemp.getTimeInMillis() - 365 * 24 * 60 * 60 * 1000);
        Helper.alarm_lastBirthday.setText("" + (365 - days) + " Days ago " + "(" + lastTemp.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ")");
    }

    private void findViews() {
        Helper.alarm_age = findViewById(R.id.alarm_age);
        Helper.alarm_date = findViewById(R.id.alarm_date);
        Helper.alarm_email = findViewById(R.id.alarm_email);
        Helper.alarm_letter = findViewById(R.id.alarm_letter);
        Helper.alarm_relation = findViewById(R.id.alarm_relation);
        Helper.alarm_phone = findViewById(R.id.alarm_phone);
        Helper.alarm_photo = findViewById(R.id.alarm_photo);
        Helper.alarm_name = findViewById(R.id.alarm_name);
        Helper.alarm_nextBirthday = findViewById(R.id.alarm_nextbd);
        Helper.alarm_lastBirthday = findViewById(R.id.alarm_lastbd);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
