package com.seeds.seeds_birthdayreminder.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seeds.seeds_birthdayreminder.R;
import com.seeds.seeds_birthdayreminder.Technical.Helper;


public class BirthdayOverview extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_birthday_overview, container, false);
        findViews(view);
        handleListeners();
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        Helper.mainActivity_centerText.setText("Birthday");
        Helper.mainActivity_leftIcon.setVisibility(View.VISIBLE);
    }

    private void handleListeners() {

    }

    private void findViews(View view) {
        Helper.overview_age = view.findViewById(R.id.overview_age);
        Helper.overview_date = view.findViewById(R.id.overview_age);
        Helper.overview_email = view.findViewById(R.id.overview_age);
        Helper.overview_letter = view.findViewById(R.id.overview_age);
        Helper.overview_relation = view.findViewById(R.id.overview_age);
        Helper.overview_phone = view.findViewById(R.id.overview_age);
        Helper.overview_name = view.findViewById(R.id.overview_age);
        Helper.overview_nextBirthday = view.findViewById(R.id.overview_age);
        Helper.overview_lastBirthday = view.findViewById(R.id.overview_age);
    }
}
