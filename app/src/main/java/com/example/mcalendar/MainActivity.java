package com.example.mcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());

    CompactCalendarView calendarView;
    TextView selectedDate;
    TextView showingMonth;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd.M.yyyy", Locale.getDefault());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.CV_calendar);
        selectedDate = findViewById(R.id.TV_date_main_activity);
        selectedDate.setText(dateFormatForDisplaying.format(currentCalender.getTimeInMillis()));
        showingMonth = findViewById(R.id.TV_month_main);
        showingMonth.setText(dateFormatForMonth.format(calendarView.getFirstDayOfCurrentMonth()));

        calendarView.setFirstDayOfWeek(2);
        calendarView.displayOtherMonthDays(true);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            DateFragment dateFragment;
            FragmentTransaction fragmentTransaction;

            @Override
            public void onDayClick(Date dateClicked) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateClicked);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (dateFragment!= null) {
                    fragmentTransaction.remove(dateFragment);
                }
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH)+1;
                int year = cal.get(Calendar.YEAR);
                selectedDate.setText(dayOfMonth + "." + month + "." + year);
                dateFragment = new DateFragment(year,month,dayOfMonth);
                fragmentTransaction.add(R.id.FL_date_fragment,dateFragment,"dateFragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                showingMonth.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                selectedDate.setText(dateFormatForDisplaying.format(firstDayOfNewMonth));

                if (dateFragment!= null) {
                    fragmentTransaction.remove(dateFragment);
                    Log.d("Main", "onMonthScroll: removing fragment");
                }

            }
        });
                //setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
//            DateFragment dateFragment;
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                month++;
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                if (dateFragment!= null) {
//                    fragmentTransaction.remove(dateFragment);
//                }
//                selectedDate.setText(dayOfMonth + "." + month + "." + year);
//                dateFragment = new DateFragment(year,month,dayOfMonth);
//                fragmentTransaction.add(R.id.FL_date_fragment,dateFragment,"dateFragment");
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//            }
//        });

        
    }
}
