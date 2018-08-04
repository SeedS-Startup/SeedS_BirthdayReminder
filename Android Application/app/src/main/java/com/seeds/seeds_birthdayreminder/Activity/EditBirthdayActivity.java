package com.seeds.seeds_birthdayreminder.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.google.gson.Gson;
import com.seeds.seeds_birthdayreminder.Configuration.AlarmHandler;
import com.seeds.seeds_birthdayreminder.Configuration.Convertor;
import com.seeds.seeds_birthdayreminder.DatabaseHelper.DatabaseHelper;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.Management.MainActivity;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import me.tom.image.picker.activity.FolderPickerActivity;
import me.tom.image.picker.model.Image;

public class EditBirthdayActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private static BirthdayEvent event;
    private static Calendar calendar = Calendar.getInstance();
    private Image image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_birthday);
        Log.d("RFVC", "" + getIntent().getExtras().getInt("ID"));
        Cursor cursor = DatabaseHelper.getInstance(this).getData(getIntent().getExtras().getInt("ID"));
        event = new BirthdayEvent(cursor.getString(1),
                cursor.getInt(6),
                cursor.getString(3), cursor.getString(5),
                new Gson().fromJson(cursor.getString(7), Calendar.class));
        event.setPhoneNumber(cursor.getString(8));
        event.setEmailAddress(cursor.getString(4));
        event.setID(cursor.getInt(0));
        event.setPicture(cursor.getString(2));
        calendar = event.getBirthDate();


        findViews();
        init();
        manageListeners();
    }

    private void manageListeners() {
        Helper.editBirthday_date.setOnClickListener(v -> {
            TimePickerDialog.newInstance(EditBirthdayActivity.this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show(getFragmentManager(), "timePicker");
            DatePickerDialog.newInstance(EditBirthdayActivity.this, calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
        });
        Helper.editBirthday_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditBirthdayActivity.this, FolderPickerActivity.class);
                ;
                intent.putExtra("multiple", false);
                startActivityForResult(intent, FolderPickerActivity.REQUEST_IMAGE_PICKER);
            }
        });
        Helper.editBirthday_saveBtn.setOnClickListener(v -> {
            if (Helper.editBirthday_name.getText().toString() == null || Helper.editBirthday_name.getText().toString().isEmpty() ||
                    Helper.editBirthday_age.getText().toString() == null || Helper.editBirthday_age.getText().toString().isEmpty() ||
                    Helper.editBirthday_relation.getText().toString() == null || Helper.editBirthday_relation.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fill empty fields ", Toast.LENGTH_LONG).show();
            } else {
                int age = Convertor.getDaysBetween(calendar, Calendar.getInstance());

                event = new BirthdayEvent(event.getID(), Helper.editBirthday_name.getText().toString(),
                        Integer.parseInt(Helper.editBirthday_age.getText().toString()),
                        Helper.editBirthday_relation.getText().toString(),
                        Helper.editBirthday_letter.getText().toString(),
                        calendar);

                event.setPicture(image.path);
                event.setPhoneNumber(Helper.editBirthday_phone.getText().toString());
                event.setEmailAddress(Helper.editBirthday_email.getText().toString());
                event.setBirthDate(calendar);

                AlarmHandler.getInstance(this).remind(this, event);
                if (DatabaseHelper.getInstance(this).updateData(event.getID(), event)) {
                    Toast.makeText(this, "Alarm set successfully", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }


    private void init() {
        Helper.editBirthday_age.setText("" + event.getAge());
        Helper.editBirthday_name.setText(event.getFullName());
        Helper.editBirthday_phone.setText(event.getPhoneNumber());
        Helper.editBirthday_email.setText(event.getEmailAddress());
        Helper.editBirthday_letter.setText(event.getLetter());
        if (event.getPicture() == null)
            Helper.editBirthday_photo.setImageResource(R.mipmap.balloon);
        else
            Helper.editBirthday_photo.setImageBitmap(BitmapFactory.decodeFile(event.getPicture()));
        Helper.editBirthday_relation.setText(event.getRelationToThePublisher());
        Calendar calendar = event.getBirthDate();
        Helper.editBirthday_date.setText(calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "  " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
    }

    private void findViews() {
        Helper.editBirthday_age = findViewById(R.id.editBirthday_age);
        Helper.editBirthday_name = findViewById(R.id.editBirthday_name);
        Helper.editBirthday_phone = findViewById(R.id.editBirthday_phone);
        Helper.editBirthday_email = findViewById(R.id.editBirthday_email);
        Helper.editBirthday_date = findViewById(R.id.editBirthday_date);
        Helper.editBirthday_letter = findViewById(R.id.editBirthday_letter);
        Helper.editBirthday_relation = findViewById(R.id.editBirthday_relation);
        Helper.editBirthday_back = findViewById(R.id.editBirthday_back);
        Helper.editBirthday_saveBtn = findViewById(R.id.editBirthday_save);
        Helper.editBirthday_photo = findViewById(R.id.editBirthday_photo);
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        Helper.editBirthday_date.setText(calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "  " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        Helper.editBirthday_age.setText("" + Convertor.getDaysBetween(Calendar.getInstance(), calendar) / 365);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        Helper.editBirthday_date.setText(calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "  " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<Image> images = new ArrayList<>();
        if (requestCode == FolderPickerActivity.REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
            ArrayList<CharSequence> imageArrayList = data.getCharSequenceArrayListExtra("images");
            for (int index = 0; index < imageArrayList.size(); index++) {
                Image image = new Image();
                image.path = imageArrayList.get(index).toString();
                images.add(image);
            }
        }
        image = images.get(0);
        Helper.editBirthday_photo.setImageBitmap(BitmapFactory.decodeFile(images.get(0).path));
    }
}
