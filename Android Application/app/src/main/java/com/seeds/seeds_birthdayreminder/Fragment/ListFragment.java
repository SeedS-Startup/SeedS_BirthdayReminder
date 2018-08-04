package com.seeds.seeds_birthdayreminder.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.seeds.seeds_birthdayreminder.Adapter.BirthdayListAdapter;
import com.seeds.seeds_birthdayreminder.DatabaseHelper.DatabaseHelper;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class ListFragment extends Fragment {
    private List<BirthdayEvent> items = new LinkedList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        Helper.mainActivity_leftIcon.setVisibility(View.INVISIBLE);
        Helper.mainActivity_centerText.setText("Up Coming");
        tuneRecyclerView(view);
        Helper.mainActivity_leftLayoutIcon.setImageResource(R.mipmap.birthday_green);
        Helper.mainActivity_rightLayoutIcon.setImageResource(R.mipmap.calendar_grey);

        return view;
    }

    private void tuneRecyclerView(View view) {
        Helper.birthdayLayoutManager = new LinearLayoutManager(getContext());
        Helper.birthdayRecyclerView.setHasFixedSize(true);
        Helper.birthdayRecyclerView.setLayoutManager(Helper.birthdayLayoutManager);
        Cursor cursor=DatabaseHelper.getInstance(view.getContext()).getAllData();
        while (cursor.moveToNext())
        {
            Log.d("FGCSE",cursor.getString(5));
            BirthdayEvent event=new BirthdayEvent(cursor.getInt(0),cursor.getString(1),
                    cursor.getInt(6),
                    cursor.getString(3),cursor.getString(5),
                    new Gson().fromJson(cursor.getString(7),Calendar.class));
            event.setPhoneNumber(cursor.getString(8));
            event.setEmailAddress(cursor.getString(4));
            event.setPicture(cursor.getString(2));
            items.add(event);
        }
        Helper.birthdayAdapter = new BirthdayListAdapter(items, view.getContext(), Helper.birthdayRecyclerView);
        Helper.birthdayRecyclerView.setAdapter(Helper.birthdayAdapter);
        /*((BirthdayListAdapter) Helper.birthdayAdapter).setLoadMoreListener(() -> {

            Helper.birthdayRecyclerView.post(() -> {
                int index = items.size() - 1;
                view.getContext().loadMore(getContext(), index);// a method which requests remote data
            });
            //Calling loadMore function in Runnable to fix the
            // java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling error
        });*/
    }

    private void findViews(View view) {
        Helper.birthdayRecyclerView = view.findViewById(R.id.birthday_recylerView);
    }
}