package com.seeds.seeds_birthdayreminder.Configuration;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.seeds.seeds_birthdayreminder.Alarm.AlarmReceiver;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmHandler {
    private static AlarmHandler instance;
    private static PendingIntent pendingIntent;
    private Context context;

    private AlarmHandler() {
    }


    public static AlarmHandler getInstance(Context context) {
        if (instance == null)
            instance = new AlarmHandler();
        return instance;
    }


    public void remind(Context context, BirthdayEvent birthdayEvent) {
        this.context = context;
        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ID",birthdayEvent.getID());
        Log.d("FGHCX2",""+birthdayEvent.getID());

        alarmIntent.putExtras(bundle);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        start(birthdayEvent);
    }

    public void start(BirthdayEvent event) {
        long i = event.getBirthDate().getTimeInMillis()-Calendar.getInstance().getTimeInMillis();
        Intent intent = new Intent(context, AlarmReceiver.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ID",event.getID());
        Log.d("FGHCX3",""+event.getID());
        intent.putExtras(bundle);
        pendingIntent = PendingIntent.getBroadcast(
                context, 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + i, pendingIntent);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
                event.getBirthDate().getActualMaximum(Calendar.DAY_OF_MONTH)*24*60*60*1000,pendingIntent);
        Toast.makeText(context, "Alarm set",Toast.LENGTH_LONG).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(context, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {

    }

}
