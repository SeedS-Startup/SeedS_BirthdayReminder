package com.seeds.seeds_birthdayreminder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seeds.seeds_birthdayreminder.Entity.CalendarItem;
import com.seeds.seeds_birthdayreminder.R;

import java.util.List;

public class CalendarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CalendarItem> calendarItems;
    private Context context;
    private  RecyclerView calendarRecyclerView;
    public CalendarListAdapter(List<CalendarItem> items, Context context, RecyclerView calendarRecyclerView) {
        calendarItems=items;
        this.context=context;
        this.calendarRecyclerView=calendarRecyclerView;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item_xml,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CalendarItem calendarItem=calendarItems.get(position);

    }

    @Override
    public int getItemCount() {
        return calendarItems.size();
    }
}
