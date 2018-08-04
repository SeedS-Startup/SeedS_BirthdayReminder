package com.seeds.seeds_birthdayreminder.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.seeds.seeds_birthdayreminder.DatabaseHelper.DatabaseHelper;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.Entity.CalendarItem;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CalendarItem> calendarItems;
    private Context context;
    private RecyclerView calendarRecyclerView;

    public CalendarListAdapter(List<CalendarItem> items, Context context, RecyclerView calendarRecyclerView) {
        calendarItems = items;
        this.context = context;
        this.calendarRecyclerView = calendarRecyclerView;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View view;

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

        // Get the number of days in that month
        Calendar test_calendar = Calendar.getInstance();
        test_calendar.set(calendarItem.getYear(), calendarItem.getMonth(), 1);
        int daysInMonth = test_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < 31; i++) {
            int finalI = i + 1;
            if (i + 1 > daysInMonth)
                items[i].setVisibility(View.INVISIBLE);
            else {
                items[i].setVisibility(View.VISIBLE);
                items[i].setText(calendarItem.getDays().get(i).getDayNumber() + "");
                items[i].setTextColor(Color.BLACK);
                items[i].setOnClickListener(v -> {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.set(calendarItem.getYear(), calendarItem.getMonth(), finalI);
                    if (DatabaseHelper.getInstance(v.getContext()).existEventIn(calendar2)) {
                        Log.d("CXCBN", "YES");
                        List<BirthdayEvent> list = DatabaseHelper.getInstance(v.getContext()).getEventsIn(calendar2);
                        Log.d("CXCBN", "" + list.size());
                        for (BirthdayEvent event : list)
                            Toast.makeText(holder.itemView.getContext(), "Birthday of " + event.getFullName(),
                                    Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(holder.itemView.getContext(), "# " + calendar2.get(Calendar.YEAR) + "/"
                                + calendar2.get(Calendar.MONTH) + "/" + calendar2.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_SHORT).show();


                    //and open a dialog to show day info
                });
                test_calendar.set(Calendar.DAY_OF_MONTH, finalI);
                if (DatabaseHelper.getInstance(holder.itemView.getContext()).existEventIn(test_calendar)) {
                    items[i].setTextColor(Color.parseColor("#61d000"));
                    Log.d("CXCBN", "I COLORED " + test_calendar.get(Calendar.YEAR) + "/" + test_calendar.get(Calendar.MONTH) + "/" +
                            test_calendar.get(Calendar.DAY_OF_MONTH));
                }
                Calendar today=Calendar.getInstance();
                if(today.get(Calendar.YEAR)==calendarItem.getYear() &&
                        today.get(Calendar.MONTH)==calendarItem.getMonth() &&
                        today.get(Calendar.DAY_OF_MONTH)== i )
                {
                    items[i].setTextColor(Color.WHITE);
                    items[i].setTextSize(20);
                    items[i].setBackgroundColor(Color.GREEN);
                }
            }
        }

        monthName.setText(calendarItem.getMonthName() + " , " + calendarItems.get(position).getYear());
        Helper.d1 = holder.itemView.findViewById(R.id.d1);
        Helper.d2 = holder.itemView.findViewById(R.id.d2);
        Helper.d3 = holder.itemView.findViewById(R.id.d3);
        Helper.d4 = holder.itemView.findViewById(R.id.d4);
        Helper.d5 = holder.itemView.findViewById(R.id.d5);
        Helper.d6 = holder.itemView.findViewById(R.id.d6);
        Helper.d7 = holder.itemView.findViewById(R.id.d7);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendarItem.getYear());
        calendar.set(Calendar.MONTH, calendarItem.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Helper.d1.setText("" + getFirstTwoOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())));
        Helper.d1.setTextColor(Color.RED);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        Helper.d2.setText("" + getFirstTwoOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())));
        Helper.d2.setTextColor(Color.RED);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        Helper.d3.setText("" + getFirstTwoOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())));
        Helper.d3.setTextColor(Color.RED);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        Helper.d4.setText("" + getFirstTwoOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())));
        Helper.d4.setTextColor(Color.RED);
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        Helper.d5.setText("" + getFirstTwoOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())));
        Helper.d5.setTextColor(Color.RED);
        calendar.set(Calendar.DAY_OF_MONTH, 6);
        Helper.d6.setText("" + getFirstTwoOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())));
        Helper.d6.setTextColor(Color.RED);
        calendar.set(Calendar.DAY_OF_MONTH, 7);
        Helper.d7.setText("" + getFirstTwoOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())));
        Helper.d7.setTextColor(Color.RED);
    }

    private String getFirstTwoOf(String displayName) {
        if (displayName == null || displayName.length() < 2)
            return null;
        StringBuilder builder = new StringBuilder();
        builder.append(displayName.substring(0, 1).toUpperCase());
        builder.append(displayName.toLowerCase().charAt(1));
        return builder.toString();
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
