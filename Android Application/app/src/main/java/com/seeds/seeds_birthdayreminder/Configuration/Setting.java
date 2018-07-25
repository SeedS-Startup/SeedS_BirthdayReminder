package com.seeds.seeds_birthdayreminder.Configuration;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Setting {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    public static final String USER_INFORMATION_SHARED_PREFERENCES_TABLE = "USER_INFORMATION";

    private Setting() {
    }

    public static void saveSetting(Context context, String table, String key, String value) {
        preferences = context.getSharedPreferences(table, MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
        editor.apply();
    }

    public static String loadSetting(Context context, String table, String key, String defaultValue) {
        preferences = context.getSharedPreferences(table, MODE_PRIVATE);
        String value = preferences.getString(key, defaultValue);
        return value;
    }
}
