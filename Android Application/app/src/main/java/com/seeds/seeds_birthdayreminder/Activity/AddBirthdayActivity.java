package com.seeds.seeds_birthdayreminder.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
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

public class AddBirthdayActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private Calendar calendar = Calendar.getInstance();  // TODO: 2018-07-25 must be inited
    private Image image = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_add_birthday);
        findViews();
        handleListeners();
    }

    private void hideStatusBar() {
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }

    private void handleListeners() {
        Helper.addBirthday_back.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(AddBirthdayActivity.this, MainActivity.class));
        });
        Helper.addBirthday_photo.setOnClickListener(v -> {
            // start activity
            Intent intent = new Intent(this, FolderPickerActivity.class);
            intent.putExtra("multiple", false);
            startActivityForResult(intent, FolderPickerActivity.REQUEST_IMAGE_PICKER);
        });
        Helper.addBirthday_date.setOnClickListener(v -> {
            TimePickerDialog.newInstance(AddBirthdayActivity.this, 12, 0, false).show(getFragmentManager(), "timePicker");
            DatePickerDialog.newInstance(AddBirthdayActivity.this, 1985, 0, 1).show(getFragmentManager(), "datePicker");
        });
        Helper.addBirthday_saveBtn.setOnClickListener(v -> {
            if (Helper.addBirthday_name.getText().toString() == null || Helper.addBirthday_name.getText().toString().isEmpty() ||
                    Helper.addBirthday_age.getText().toString() == null || Helper.addBirthday_age.getText().toString().isEmpty() ||
                    Helper.addBirthday_relation.getText().toString() == null || Helper.addBirthday_relation.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fill empty fields ", Toast.LENGTH_LONG).show();
            } else {
                int age = Convertor.getDaysBetween(calendar, Calendar.getInstance());

                BirthdayEvent birthdayEvent = new BirthdayEvent(Helper.addBirthday_name.getText().toString(),
                        Integer.parseInt(Helper.addBirthday_age.getText().toString()),
                        Helper.addBirthday_relation.getText().toString(),
                        Helper.addBirthday_letter.getText().toString(),
                        calendar);

                if (image == null || image.path == null)
                    birthdayEvent.setPicture(null);
                else
                    birthdayEvent.setPicture(image.path);
                birthdayEvent.setPhoneNumber(Helper.addBirthday_phone.getText().toString());
                birthdayEvent.setEmailAddress(Helper.addBirthday_email.getText().toString());
                birthdayEvent.setBirthDate(calendar);

                if (DatabaseHelper.getInstance(this).insertData(birthdayEvent)) {
                    AlarmHandler.getInstance(AddBirthdayActivity.this).remind(AddBirthdayActivity.this, birthdayEvent);
                    Toast.makeText(this, "Alarm set successfully", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();


            }
        });

    }

    private void findViews() {
        Helper.addBirthday_name = findViewById(R.id.addBirthday_name);
        Helper.addBirthday_date = findViewById(R.id.addBirthday_date);
        Helper.addBirthday_email = findViewById(R.id.addBirthday_email);
        Helper.addBirthday_phone = findViewById(R.id.addBirthday_phone);
        Helper.addBirthday_letter = findViewById(R.id.addBirthday_letter);
        Helper.addBirthday_age = findViewById(R.id.addBirthday_age);
        Helper.addBirthday_relation = findViewById(R.id.addBirthday_relation);
        Helper.addBirthday_back = findViewById(R.id.addBirthday_back);
        Helper.addBirthday_saveBtn = findViewById(R.id.add_birthday_save);
        Helper.addBirthday_photo = findViewById(R.id.add_birthday_add_photo);
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        Helper.addBirthday_date.setText(calendar.get(Calendar.YEAR) + "/" +
                (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "  " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        Helper.addBirthday_age.setText("" + Convertor.getDaysBetween(Calendar.getInstance(), calendar) / 365);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        Helper.addBirthday_date.setText(calendar.get(Calendar.YEAR) + "/" +
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
        try {
            image = images.get(0);
            Helper.addBirthday_photo.setImageBitmap(BitmapFactory.decodeFile(images.get(0).path));
        } catch (Exception e) {
        }
    }
}
