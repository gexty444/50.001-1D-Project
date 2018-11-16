package com.example.dominic.cody;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

public class Calendar extends AppCompatActivity {

    CalendarView calendarView;
    ImageView imageView;
    int imageResource;
    String TAG = "Logcat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        calendarView = findViewById(R.id.calendarView);
        imageView = findViewById(R.id.imageView);
//        imageResource = 0;

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = String.format("%d%02d%02d", year, month+1, dayOfMonth); //ISO 8601 compliant
                Log.i(TAG, date); //Logcat for the date selected

                imageResource = getResources().getIdentifier("@drawable/" + date, null, getApplicationContext().getPackageName());
                Log.i(TAG,""+ imageResource);
            }
        });
    }

}
