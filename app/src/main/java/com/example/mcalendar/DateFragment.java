package com.example.mcalendar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private int mYear;
    private int mMonth;
    private int mDayOfMonth;


    public DateFragment() {
        // Required empty public constructor
    }

    public DateFragment(int mYear, int mMonth, int mDayOfMonth) {
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDayOfMonth = mDayOfMonth;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        TextView dateText = view.findViewById(R.id.TV_fragment_date);
        dateText.setText(mDayOfMonth + "." + mMonth + "." + mYear);
        return view;
    }

}
