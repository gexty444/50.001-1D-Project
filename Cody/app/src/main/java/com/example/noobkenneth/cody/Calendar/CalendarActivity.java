package com.example.noobkenneth.cody.Calendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.ImageView;

import com.example.noobkenneth.cody.Customise.CustomiseActivity;
import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.Wardrobe.WardrobeActivity;
import com.example.noobkenneth.cody.database.RecyclerViewActivity;

import java.text.SimpleDateFormat;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    ImageView imageView;
    int imageResource;
    int today;
    String TAG = "Logcat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //This part dictates the behaviour of the bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        //sets the selected bottom bar item
        bottomNavigationView.setSelectedItemId(R.id.navigation_ootds);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Log.i("Logcat", "home pressed from CalendarActivity");
                        Intent intent_home = new Intent(CalendarActivity.this, MainActivity.class);
                        intent_home.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_home);
                        break;
                    case R.id.navigation_wardrobe:
                        Log.i("Logcat", "wardrobe pressed from CalendarActivity");
                        Intent intent_wardrobe = new Intent(CalendarActivity.this, RecyclerViewActivity.class);
                        intent_wardrobe.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_wardrobe);
                        break;
                    case R.id.navigation_ootds:
                        Log.i("Logcat", "ootds pressed from CalendarActivity");
//                        Intent intent_ootds = new Intent(CalendarActivity.this, CalendarActivity.class);
//                        startActivity(intent_ootds);
                        break;
                    case R.id.navigation_customise:
                        Log.i("Logcat", "customise pressed from CalendarActivity");
                        Intent intent_customise = new Intent(CalendarActivity.this, CustomiseActivity.class);
                        intent_customise.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_customise);
                        break;
                    case R.id.navigation_profile:
                        Log.i("Logcat", "profile pressed from CalendarActivity");
                        break;
                }
                return false;
            }
        });

        //developer defined widgets
        calendarView = findViewById(R.id.calendarCalendarView);
        imageView = findViewById(R.id.calendarImageView);

        //get today's date
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        today = Integer.parseInt(dateFormat.format(calendarView.getDate()));

        //this sets the imageView image to the OOTD taken today
        imageResource = getResources().getIdentifier("@drawable/ootd" + today, null, ""+getApplicationContext().getPackageName());
        Log.i(TAG,"imageResource: "+ imageResource);
        imageView.setImageResource(imageResource);

        //when a date on the calendar is selected
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = String.format("%d%02d%02d", year, month+1, dayOfMonth); //ISO 8601 compliant
                Log.i(TAG, "Date selected: " +date); //Logcat for the date selected

                //this sets the imageView image to a file with the date selected
                imageResource = getResources().getIdentifier("@drawable/ootd" + date, null, ""+getApplicationContext().getPackageName());
                Log.i(TAG,"imageResource: "+ imageResource);
                imageView.setImageResource(imageResource);
            }
        });
    }
}
