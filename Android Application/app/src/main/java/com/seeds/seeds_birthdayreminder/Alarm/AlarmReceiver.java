package com.seeds.seeds_birthdayreminder.Alarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.seeds.seeds_birthdayreminder.Activity.AlarmActivity;
import com.seeds.seeds_birthdayreminder.Management.MainActivity;
import com.seeds.seeds_birthdayreminder.R;

public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp= MediaPlayer.create(context, R.raw.alarm);
        mp.start();
        Intent intent1=new Intent(context, AlarmActivity.class);
        Bundle bundle=new Bundle();
        Log.d("FGHCX",""+intent.getExtras().getInt("ID"));
        bundle.putInt("ID",intent.getExtras().getInt("ID"));
        intent1.putExtras(bundle);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}