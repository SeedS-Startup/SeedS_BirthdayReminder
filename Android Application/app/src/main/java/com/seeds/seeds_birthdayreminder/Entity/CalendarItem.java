package com.seeds.seeds_birthdayreminder.Entity;

import android.view.TextureView;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

public class CalendarItem {
    private int year;
    private int month;
    private Set<DayItem> days;  //size : 31 or 30

    public CalendarItem(int year, int month, Set<DayItem> days) {
        this.year = year;
        this.month = month;
        this.days = days;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Set<DayItem> getDays() {
        return days;
    }

    public void setDays(Set<DayItem> days) {
        this.days = days;
    }
}
