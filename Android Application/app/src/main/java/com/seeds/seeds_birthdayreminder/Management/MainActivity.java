package com.seeds.seeds_birthdayreminder.Management;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.seeds.seeds_birthdayreminder.Activity.AddBirthdayActivity;
import com.seeds.seeds_birthdayreminder.Activity.SettingActivity;
import com.seeds.seeds_birthdayreminder.Fragment.CalendarFragment;
import com.seeds.seeds_birthdayreminder.Fragment.ListFragment;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        hideStatusBar();
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
                    .setOnSystemUiVisibilityChangeListener(visibility -> {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            decorView.setSystemUiVisibility(flags);
                        }
                    });
        }
    }

    private void handleListeners() {
        Helper.mainActivity_leftLayout.setOnClickListener(this);
        Helper.mainActivity_rightLayout.setOnClickListener(this);
        Helper.mainActivity_fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddBirthdayActivity.class)));
        Helper.mainActivity_setting.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingActivity.class)));
    }

    private void findViews() {
        Helper.mainActivity_leftLayout = findViewById(R.id.left_fragment_view);
        Helper.mainActivity_rightLayout = findViewById(R.id.right_fragment_view);
        Helper.mainActivity_leftLayoutIcon = findViewById(R.id.left_layout_icon);
        Helper.mainActivity_rightLayoutIcon = findViewById(R.id.right_layout_icon);
        Helper.mainActivity_fab = findViewById(R.id.add_birthday_icon);
        Helper.mainActivity_setting = findViewById(R.id.main_setting_icon);
        Helper.mainActivity_leftIcon = findViewById(R.id.main_back_icon);
        Helper.mainActivity_centerText = findViewById(R.id.center_text);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.left_fragment_view:
                fragment = new ListFragment();
                break;
            case R.id.right_fragment_view:
                fragment = new CalendarFragment();
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.main_fragment, fragment)
                .commit();
    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
