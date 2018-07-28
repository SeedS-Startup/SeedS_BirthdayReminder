package com.seeds.seeds_birthdayreminder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.seeds.seeds_birthdayreminder.Entity.CalendarItem;

import java.util.List;

public class CalendarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public CalendarListAdapter(List<CalendarItem> items, Context context, RecyclerView calendarRecyclerView) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
