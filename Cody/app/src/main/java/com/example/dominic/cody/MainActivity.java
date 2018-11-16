package com.example.dominic.cody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //starts calendar activity by default for debugging
        Intent start_calendar = new Intent(this, Calendar.class);
        startActivity(start_calendar);
    }
}
