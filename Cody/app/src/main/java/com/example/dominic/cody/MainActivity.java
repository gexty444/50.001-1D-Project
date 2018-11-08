package com.example.dominic.cody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // temporary buttons
//    Button toWardrobe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toWardrobe(View view) // called onClick from "to wardrobe" button
    {
        Intent intent = new Intent(this, WardrobeActivity.class); // intent calls another activity or application
        startActivity(intent);
    }
}
