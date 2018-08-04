package com.seeds.seeds_birthdayreminder.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seeds.seeds_birthdayreminder.Adapter.BirthdayListAdapter;
import com.seeds.seeds_birthdayreminder.Adapter.CalendarListAdapter;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.Entity.CalendarItem;
import com.seeds.seeds_birthdayreminder.Entity.DayItem;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.util.LinkedList;
import java.util.List;

public class CalendarFragment extends Fragment {
    private List<CalendarItem> items = new LinkedList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        findViews(view);
        Helper.mainActivity_leftIcon.setVisibility(View.INVISIBLE);
        Helper.mainActivity_centerText.setText("Calendar");
        tuneRecyclerView(view);
        Helper.mainActivity_leftLayoutIcon.setImageResource(R.mipmap.birthday_grey);
        Helper.mainActivity_rightLayoutIcon.setImageResource(R.mipmap.calendar_green);
        return view;
    }

    private void tuneRecyclerView(View view) {
        Helper.calendarLayoutManager = new LinearLayoutManager(getContext());
        Helper.calendarRecyclerView.setHasFixedSize(true);
        Helper.calendarRecyclerView.setLayoutManager(Helper.calendarLayoutManager);
        initializeCalendars();
        Helper.calendarAdapter = new CalendarListAdapter(items, view.getContext(), Helper.calendarRecyclerView);
       /* ((BirthdayListAdapter) Helper.calendarAdapter).setLoadMoreListener(() -> {

            Helper.calendarRecyclerView.post(() -> {
                int index = items.size() - 1;
                view.getContext().loadMore(getContext(), index);// a method which requests remote data
            });
            //Calling loadMore function in Runnable to fix the
            // java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling error
        });*/
        Helper.calendarRecyclerView.setAdapter(Helper.calendarAdapter);
    }

    private void initializeCalendars() {
        CalendarItem calendar;
        List<DayItem> list=new LinkedList<>();
        for (int i = 2018; i < 2119; i++) {
            for (int j = 0; j < 12; j++) {
                for(int k=1;k<=31;k++)
                    list.add(new DayItem(k));
                calendar = new CalendarItem(i, j, list);

                items.add(calendar);
            }
        }
    }

    private void findViews(View view) {
        Helper.calendarRecyclerView = view.findViewById(R.id.calendar_recylerView);
    }
}