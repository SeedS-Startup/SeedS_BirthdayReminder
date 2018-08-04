package com.seeds.seeds_birthdayreminder.Entity;

import android.view.TextureView;
import android.view.View;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalendarItem {
    private int year;
    private int month;
    private List<DayItem> days;  //size : 31 or 30

    public CalendarItem(int year, int month, List<DayItem> days) {
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

    public List<DayItem> getDays() {
        return days;
    }

    public void setDays(List<DayItem> days) {
        this.days = days;
    }

    public String getMonthName()
    {
        if(month<0 || month>11)
            return "not set";
        switch (month)
        {
            case 0:return "January";
            case 1:return "February";
            case 2:return "March";
            case 3:return "April";
            case 4:return "May";
            case 5:return "June";
            case 6:return "July";
            case 7:return "August";
            case 8:return "September";
            case 9:return "October";
            case 10:return "November";
            case 11:return "December";
        }
        return null;
    }
}
