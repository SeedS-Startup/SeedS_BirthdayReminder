package com.seeds.seeds_birthdayreminder.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.seeds.seeds_birthdayreminder.Configuration.Convertor;
import com.seeds.seeds_birthdayreminder.DatabaseHelper.DatabaseHelper;
import com.seeds.seeds_birthdayreminder.Entity.BirthdayEvent;
import com.seeds.seeds_birthdayreminder.Fragment.BirthdayOverview;
import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BirthdayListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BirthdayEvent> birthdayEvents;
    private Context context;
    private RecyclerView birthdayRecyclerView;

    public BirthdayListAdapter(List<BirthdayEvent> items, Context context, RecyclerView birthdayRecyclerView) {
        birthdayEvents = items;
        this.context = context;
        this.birthdayRecyclerView = birthdayRecyclerView;

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
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.birthday_item_xml, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BirthdayEvent birthdayEvent = birthdayEvents.get(position);
        TextView age = holder.itemView.findViewById(R.id.age);
        TextView fullName = holder.itemView.findViewById(R.id.fullName);
        TextView day = holder.itemView.findViewById(R.id.day);
        TextView month = holder.itemView.findViewById(R.id.month);
        TextView year = holder.itemView.findViewById(R.id.year);
        TextView daysToEvent = holder.itemView.findViewById(R.id.days_to_event);
        ImageView picture = holder.itemView.findViewById(R.id.picture);
        View item = holder.itemView.findViewById(R.id.birthdate_whole_item);

        age.setText("" + birthdayEvent.getAge());
        fullName.setText(birthdayEvent.getFullName());
        month.setText(birthdayEvent.getBirthDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " ");
        year.setText("" + birthdayEvent.getBirthDate().get(Calendar.YEAR));
        day.setText("" + birthdayEvent.getBirthDate().get(Calendar.DAY_OF_MONTH));
        picture.setImageBitmap(BitmapFactory.decodeFile(birthdayEvent.getPicture()));

        //find the remained days toward the next birthday
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR) + 1,
                birthdayEvent.getBirthDate().get(Calendar.MONTH),
                birthdayEvent.getBirthDate().get(Calendar.DAY_OF_MONTH));
        long answer = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        int days = (int) (answer / (1000 * 60 * 60 * 24));
        if (days > 365)
            days -= 365;
        //

        daysToEvent.setText("" + days);


        item.setOnClickListener(v ->
        {
            Fragment overView = new BirthdayOverview();
            Bundle bundle = new Bundle();
            bundle.putInt("ID", birthdayEvent.getID());
            overView.setArguments(bundle);
            ((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.main_fragment, overView)
                    .commit();

        });
        item.setOnLongClickListener(v ->
        {
            AlertDialog alertDialog1;
            final CharSequence[] options = new String[]{"Delete"};
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setCancelable(true);
            builder.setItems(options, (dialog, which) -> {
                switch (which) {
                    case 0:
                        birthdayEvents.remove(birthdayEvent);
                        DatabaseHelper.getInstance(v.getContext()).deleteData(birthdayEvent.getID());
                        if (Helper.birthdayAdapter != null)
                            Helper.birthdayAdapter.notifyDataSetChanged();
                        if (Helper.calendarAdapter != null)
                            Helper.calendarAdapter.notifyDataSetChanged();

                        break;
                }
                dialog.dismiss();
            });

            alertDialog1 = builder.create();
            alertDialog1.show();

            return true;
        });

    }

    @Override
    public int getItemCount() {
        return birthdayEvents.size();
    }
}
