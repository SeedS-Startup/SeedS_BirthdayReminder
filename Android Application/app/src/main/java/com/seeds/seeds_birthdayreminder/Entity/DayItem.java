package com.seeds.seeds_birthdayreminder.Entity;

import android.widget.TextView;

public class DayItem {
    private int dayNumber;

    public DayItem(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

}
