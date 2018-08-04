package com.seeds.seeds_birthdayreminder.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.seeds.seeds_birthdayreminder.Activity.EditBirthdayActivity;
import com.seeds.seeds_birthdayreminder.Configuration.Convertor;
import com.seeds.seeds_birthdayreminder.DatabaseHelper.DatabaseHelper;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.util.Calendar;
import java.util.Locale;


public class BirthdayOverview extends Fragment {

    private static BirthdayEvent birthdayEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Cursor cursor = DatabaseHelper.getInstance(container.getContext()).getData(getArguments().getInt("ID"));
        birthdayEvent = new BirthdayEvent(getArguments().getInt("ID"), cursor.getString(1),
                cursor.getInt(6),
                cursor.getString(3), cursor.getString(5),
                new Gson().fromJson(cursor.getString(7), Calendar.class));
        birthdayEvent.setPicture(cursor.getString(2));
        birthdayEvent.setPhoneNumber(cursor.getString(8));
        birthdayEvent.setEmailAddress(cursor.getString(4));
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_birthday_overview, container, false);
        findViews(view);
        handleListeners(view);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        Helper.mainActivity_centerText.setText("Birthday");
        Helper.mainActivity_leftIcon.setVisibility(View.VISIBLE);
        Helper.overview_age.setText(birthdayEvent.getAge() + " Years old");
        Helper.overview_email.setText(birthdayEvent.getEmailAddress());
        Helper.overview_name.setText(birthdayEvent.getFullName());
        Helper.overview_relation.setText(birthdayEvent.getRelationToThePublisher());
        Helper.overview_letter.setText(birthdayEvent.getLetter());
        Helper.overview_phone.setText(birthdayEvent.getPhoneNumber());
        if (birthdayEvent.getPicture() != null)
            Helper.overview_picture.setImageBitmap(BitmapFactory.decodeFile(birthdayEvent.getPicture()));
        else
            Helper.overview_picture.setImageResource(R.mipmap.balloon);
        Helper.overview_date.setText(birthdayEvent.getBirthDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                + " " + birthdayEvent.getBirthDate().get(Calendar.DAY_OF_MONTH) + "," + birthdayEvent.getBirthDate().get(Calendar.YEAR));

        //find the remained days toward the next birthday
        Calendar calendar=Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)+1,
                birthdayEvent.getBirthDate().get(Calendar.MONTH),
                birthdayEvent.getBirthDate().get(Calendar.DAY_OF_MONTH));
        long answer=calendar.getTimeInMillis()-Calendar.getInstance().getTimeInMillis();
        int days= (int) (answer/(1000*60*60*24));
        if(days>365)
            days-=365;
        //
        Calendar nextTemp=Calendar.getInstance();
        nextTemp.setTimeInMillis((long) (Calendar.getInstance().getTimeInMillis()+days*24*60*60*1000));

        Helper.overview_nextBirthday.setText("In "+days+" Days("+nextTemp.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.getDefault())+")");

        Calendar lastTemp=Calendar.getInstance();
        lastTemp.setTimeInMillis(nextTemp.getTimeInMillis()-365*24*60*60*1000);
        Helper.overview_lastBirthday.setText(""+(365-days) + " Days ago "+"("+lastTemp.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.getDefault())+")");
    }

    private void handleListeners(View view) {
        Helper.overview_editButton.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), EditBirthdayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ID", birthdayEvent.getID());
            intent.putExtras(bundle);
            startActivity(intent);
            ((Activity) view.getContext()).finish();
        });

    }

    private void findViews(View view) {
        Helper.overview_age = view.findViewById(R.id.overview_age);
        Helper.overview_date = view.findViewById(R.id.overview_date);
        Helper.overview_email = view.findViewById(R.id.overview_email);
        Helper.overview_letter = view.findViewById(R.id.overview_letter);
        Helper.overview_relation = view.findViewById(R.id.overview_relation);
        Helper.overview_phone = view.findViewById(R.id.overview_phone);
        Helper.overview_name = view.findViewById(R.id.overview_name);
        Helper.overview_picture = view.findViewById(R.id.overview_picture);
        Helper.overview_nextBirthday = view.findViewById(R.id.overview_nextbd);
        Helper.overview_lastBirthday = view.findViewById(R.id.overview_lastbd);
        Helper.overview_editButton = view.findViewById(R.id.overview_edit_button);
    }
}
