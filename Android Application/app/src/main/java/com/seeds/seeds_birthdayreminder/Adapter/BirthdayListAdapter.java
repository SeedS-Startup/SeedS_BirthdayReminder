package com.seeds.seeds_birthdayreminder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;

import java.util.List;

public class BirthdayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public BirthdayListAdapter(List<BirthdayEvent> items, Context context, RecyclerView birthdayRecyclerView) {

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
