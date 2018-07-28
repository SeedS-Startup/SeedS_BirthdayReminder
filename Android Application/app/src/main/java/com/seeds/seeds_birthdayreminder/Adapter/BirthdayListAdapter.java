package com.seeds.seeds_birthdayreminder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.R;

import java.util.List;

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
        //holder.itemView.findViewById(R.id.fullName).setT

    }

    @Override
    public int getItemCount() {
        return birthdayEvents.size();
    }
}
