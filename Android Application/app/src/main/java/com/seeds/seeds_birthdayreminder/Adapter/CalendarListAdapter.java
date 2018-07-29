package com.seeds.seeds_birthdayreminder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.seeds.seeds_birthdayreminder.Entity.CalendarItem;
import com.seeds.seeds_birthdayreminder.R;

import java.util.List;

public class CalendarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CalendarItem> calendarItems;
    private Context context;
    private RecyclerView calendarRecyclerView;

    public CalendarListAdapter(List<CalendarItem> items, Context context, RecyclerView calendarRecyclerView) {
        calendarItems = items;
        this.context = context;
        this.calendarRecyclerView = calendarRecyclerView;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {View view;
        public MyViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item_xml, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView monthName = holder.itemView.findViewById(R.id.month_name_tv);
        TextView[] items = new TextView[31];
        newAllTextViews(items, holder.itemView);
        CalendarItem calendarItem = calendarItems.get(position);
        for (int i = 0; i < 31; i++) {
            items[i].setText(calendarItem.getDays().get(i).getDayNumber() + "");
            items[i].setOnClickListener(v -> {
                Toast.makeText(holder.itemView.getContext(), "Clicked on " + position, Toast.LENGTH_SHORT).show();
                //and open a dialog to show day info
            });
        }

        monthName.setText(calendarItem.getMonthName());

    }

    private void newAllTextViews(TextView[] items, View itemView) {
        items[0] = itemView.findViewById(R.id.tv_day_1);
        items[1] = itemView.findViewById(R.id.tv_day_2);
        items[2] = itemView.findViewById(R.id.tv_day_3);
        items[3] = itemView.findViewById(R.id.tv_day_4);
        items[4] = itemView.findViewById(R.id.tv_day_5);
        items[5] = itemView.findViewById(R.id.tv_day_6);
        items[6] = itemView.findViewById(R.id.tv_day_7);
        items[7] = itemView.findViewById(R.id.tv_day_8);
        items[8] = itemView.findViewById(R.id.tv_day_9);
        items[9] = itemView.findViewById(R.id.tv_day_10);
        items[10] = itemView.findViewById(R.id.tv_day_11);
        items[11] = itemView.findViewById(R.id.tv_day_12);
        items[12] = itemView.findViewById(R.id.tv_day_13);
        items[13] = itemView.findViewById(R.id.tv_day_14);
        items[14] = itemView.findViewById(R.id.tv_day_15);
        items[15] = itemView.findViewById(R.id.tv_day_16);
        items[16] = itemView.findViewById(R.id.tv_day_17);
        items[17] = itemView.findViewById(R.id.tv_day_18);
        items[18] = itemView.findViewById(R.id.tv_day_19);
        items[19] = itemView.findViewById(R.id.tv_day_20);
        items[20] = itemView.findViewById(R.id.tv_day_21);
        items[21] = itemView.findViewById(R.id.tv_day_22);
        items[22] = itemView.findViewById(R.id.tv_day_23);
        items[23] = itemView.findViewById(R.id.tv_day_24);
        items[24] = itemView.findViewById(R.id.tv_day_25);
        items[25] = itemView.findViewById(R.id.tv_day_26);
        items[26] = itemView.findViewById(R.id.tv_day_27);
        items[27] = itemView.findViewById(R.id.tv_day_28);
        items[28] = itemView.findViewById(R.id.tv_day_29);
        items[29] = itemView.findViewById(R.id.tv_day_30);
        items[30] = itemView.findViewById(R.id.tv_day_31);
    }

    @Override
    public int getItemCount() {
        return calendarItems.size();
    }
}
