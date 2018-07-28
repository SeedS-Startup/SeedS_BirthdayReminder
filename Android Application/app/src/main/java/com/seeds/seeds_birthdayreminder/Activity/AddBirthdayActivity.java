package com.seeds.seeds_birthdayreminder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.seeds.seeds_birthdayreminder.Configuration.AlarmHandler;
import com.seeds.seeds_birthdayreminder.Configuration.Convertor;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.Management.MainActivity;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.util.Calendar;

public class AddBirthdayActivity extends AppCompatActivity {
    private Calendar calendar=Calendar.getInstance();  // TODO: 2018-07-25 must be inited
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);
        findViews();
        handleListeners();
    }

    private void handleListeners() {
        Helper.addBirthday_back.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(AddBirthdayActivity.this, MainActivity.class));
        });
        Helper.addBirthday_setting.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(AddBirthdayActivity.this, SettingActivity.class));
        });
        Helper.addBirthday_date.setOnClickListener(v -> {

            // TODO: 2018-07-25 initial calendar object woth the selected date by user

        });
        Helper.addBirthday_saveBtn.setOnClickListener(v -> {
            if (Helper.addBirthday_name.getText().toString() == null || Helper.addBirthday_name.getText().toString().isEmpty() ||
                    Helper.addBirthday_age.getText().toString() == null || Helper.addBirthday_age.getText().toString().isEmpty() ||
                    Helper.addBirthday_relation.getText().toString() == null || Helper.addBirthday_relation.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fill empty fields ", Toast.LENGTH_LONG).show();
            }
            else
            {
                // TODO: 2018-07-25 should be set as the text of age edittext when calendar set
                //
                int age= Convertor.getDaysBetween(calendar,Calendar.getInstance());
                //

                BirthdayEvent birthdayEvent=new BirthdayEvent(Helper.addBirthday_name.getText().toString(),
                        Integer.parseInt(Helper.addBirthday_age.getText().toString()),
                        Helper.addBirthday_relation.getText().toString(),
                        Helper.addBirthday_letter.getText().toString(),
                        calendar);

                birthdayEvent.setPhoneNumber(Helper.addBirthday_phone.getText().toString());
                birthdayEvent.setEmailAddress(Helper.addBirthday_email.getText().toString());


                AlarmHandler.remind(birthdayEvent);

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
        Helper.addBirthday_setting = findViewById(R.id.main_setting_icon);
        Helper.addBirthday_back = findViewById(R.id.main_back_icon);
        Helper.addBirthday_saveBtn = findViewById(R.id.add_birthday_save);
    }
}
