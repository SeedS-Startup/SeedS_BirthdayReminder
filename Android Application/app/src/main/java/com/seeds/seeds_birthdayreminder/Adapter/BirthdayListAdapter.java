package com.seeds.seeds_birthdayreminder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BirthdayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BirthdayEvent> birthdayEvents;
    private Context context;
    private RecyclerView birthdayRecyclerView;

    public BirthdayListAdapter(List<BirthdayEvent> items, Context context, RecyclerView birthdayRecyclerView) {
        birthdayEvents=items;
        this.context=context;
        this.birthdayRecyclerView=birthdayRecyclerView;

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.birthday_item_xml,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BirthdayEvent birthdayEvent=birthdayEvents.get(position);
        TextView age=holder.itemView.findViewById(R.id.fullName);
        TextView fullName=holder.itemView.findViewById(R.id.fullName);
        TextView day=holder.itemView.findViewById(R.id.day);
        TextView month=holder.itemView.findViewById(R.id.month);
        TextView year=holder.itemView.findViewById(R.id.year);
        TextView daysToEvent=holder.itemView.findViewById(R.id.days_to_event);
        ImageView picture=holder.itemView.findViewById(R.id.picture);

        age.setText(birthdayEvent.getAge());
        fullName.setText(birthdayEvent.getFullName());
        day.setText(birthdayEvent.getBirthDate().get(Calendar.DAY_OF_MONTH));
        month.setText(birthdayEvent.getBirthDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH ));
        year.setText(birthdayEvent.getBirthDate().get(Calendar.YEAR));
        //daysToEvent.setText(birthdayEvent.);
       // picture.set


    }

    @Override
    public int getItemCount() {
        return birthdayEvents.size();
    }
}
