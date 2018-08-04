package com.seeds.seeds_birthdayreminder.Technical;

import android.app.PendingIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Helper {
    public static LinearLayout mainActivity_leftLayout;
    public static LinearLayout mainActivity_rightLayout;
    public static FloatingActionButton mainActivity_fab;
    public static ImageView mainActivity_setting;
    public static ImageView mainActivity_leftIcon;
    public static TextView mainActivity_centerText;
    public static ImageView mainActivity_rightLayoutIcon;
    public static ImageView mainActivity_leftLayoutIcon;


    public static EditText addBirthday_name;
    public static TextView addBirthday_date;
    public static EditText addBirthday_email;
    public static EditText addBirthday_phone;
    public static EditText addBirthday_letter;
    public static EditText addBirthday_age;
    public static ImageView addBirthday_photo;
    public static EditText addBirthday_relation;
    public static ImageView addBirthday_back;
    public static TextView addBirthday_saveBtn;

    public static RecyclerView birthdayRecyclerView;
    public static RecyclerView.LayoutManager birthdayLayoutManager;
    public static RecyclerView.Adapter birthdayAdapter;

    public static RecyclerView calendarRecyclerView;
    public static RecyclerView.LayoutManager calendarLayoutManager;
    public static RecyclerView.Adapter calendarAdapter;

    public static TextView overview_name;
    public static TextView overview_date;
    public static TextView overview_email;
    public static TextView overview_phone;
    public static TextView overview_letter;
    public static TextView overview_nextBirthday;
    public static TextView overview_lastBirthday;
    public static TextView overview_age;
    public static TextView overview_relation;
    public static ImageButton overview_editButton;
    public static ImageView overview_picture;

    public static EditText editBirthday_name;
    public static TextView editBirthday_date;
    public static EditText editBirthday_email;
    public static EditText editBirthday_phone;
    public static EditText editBirthday_letter;
    public static EditText editBirthday_age;
    public static EditText editBirthday_relation;
    public static ImageView editBirthday_back;
    public static TextView editBirthday_saveBtn;
    public static ImageView editBirthday_photo;

    public static TextView d1;
    public static TextView d2;
    public static TextView d3;
    public static TextView d4;
    public static TextView d5;
    public static TextView d6;
    public static TextView d7;


    private PendingIntent pendingIntent;


    public static TextView alarm_name;
    public static TextView alarm_date;
    public static TextView alarm_email;
    public static TextView alarm_phone;
    public static TextView alarm_letter;
    public static TextView alarm_nextBirthday;
    public static TextView alarm_lastBirthday;
    public static TextView alarm_age;
    public static TextView alarm_relation;
    public static ImageView alarm_photo;
}
