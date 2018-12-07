package com.example.noobkenneth.cody.database;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.example.noobkenneth.cody.Customise.Screenshot;
import com.example.noobkenneth.cody.Wardrobe.WardrobeActivity;
import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CustomiseActivity extends AppCompatActivity {
    private static final String TAG = "CustomiseActivity";


    private ImageButton imagebutton_cus1;
    private ImageButton imagebutton_cus2;
    private ImageButton imagebutton_cus3;
    private ImageButton imagebutton_cus4;
    private ImageButton imagebutton_cus5;
    private ImageButton imagebutton_cus6;

    GridLayout layoutcustomise;         // for capturing the OOTD
    Button button_capture;

    static CharaDbHelper charaDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);
        Log.d(TAG, "onCreate: Starting");

        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);


        //This part dictates the behaviour of the bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        //sets the selected bottom bar item
        bottomNavigationView.setSelectedItemId(R.id.navigation_customise);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Log.i("Logcat", "home pressed from CustomiseActivity");
                        Intent intent_home = new Intent(CustomiseActivity.this, MainActivity.class);
                        intent_home.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_home);
                        break;
                    case R.id.navigation_wardrobe:
                        Log.i("Logcat", "wardrobe pressed from CustomiseActivity");
                        Intent intent_wardrobe = new Intent(CustomiseActivity.this, WardrobeActivity.class);
                        intent_wardrobe.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_wardrobe);
                        break;
                    case R.id.navigation_ootds:
                        Log.i("Logcat", "ootds pressed from CustomiseActivity");
                        Intent intent_ootds = new Intent(CustomiseActivity.this, CalendarActivity.class);
                        intent_ootds.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_ootds);
                        break;
                    case R.id.navigation_customise:
                        Log.i("Logcat", "customise pressed from CustomiseActivity");
//                        Intent intent_customise = new Intent(CustomiseActivity.this, CustomiseActivity.class);
//                        intent_customise.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent_customise);
                        break;
                    case R.id.navigation_generate:
                        Log.i("Logcat", "generate pressed from CustomiseActivity");
                        Intent intent_recommendations = new Intent(CustomiseActivity.this, RecommendationsActivity.class);
                        intent_recommendations.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_recommendations);
                        break;
                }
                return false;
            }
        });


        // Assigning ImageButton and linking the click to a database query

        imagebutton_cus1 = (ImageButton) findViewById(R.id.imagebutton_cus1);
        imagebutton_cus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagebutton_cus1.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Tops'").getBitmap());
            }
        });


        imagebutton_cus2 = (ImageButton) findViewById(R.id.imagebutton_cus2);
        imagebutton_cus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagebutton_cus2.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Bottoms'").getBitmap());
            }
        });


        imagebutton_cus3 = (ImageButton) findViewById(R.id.imagebutton_cus3);
        imagebutton_cus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagebutton_cus3.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'One-piece'").getBitmap());
            }
        });


        imagebutton_cus4 = (ImageButton) findViewById(R.id.imagebutton_cus4);
        imagebutton_cus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagebutton_cus4.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Shoes'").getBitmap());
            }
        });


        imagebutton_cus5 = (ImageButton) findViewById(R.id.imagebutton_cus5);
        imagebutton_cus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagebutton_cus5.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Bags'").getBitmap());
            }
        });


        imagebutton_cus6 = (ImageButton) findViewById(R.id.imagebutton_cus6);
        imagebutton_cus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagebutton_cus6.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Accessories'").getBitmap());
            }
        });


        // When "wear today!" button is pressed, the outfit selected would be inserted
        // into the database

        button_capture = (Button) findViewById(R.id.button_capture);
        layoutcustomise = (GridLayout) findViewById(R.id.layout_customise);
        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap screen = Screenshot.takescreenshotOfView(v.getRootView().findViewById(R.id.layout_customise));

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                screen.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                String category = "Null";
                String formality = "Null";
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String last_used = formatter.format(date); //populates the database with default date
                boolean ootd = true; // when adding clothing into the database, it is an ootd

                CharaDbHelper.CharaData charaData = new CharaDbHelper.CharaData(category,
                        formality, last_used, ootd, bitmap);
                CustomiseActivity.charaDbHelper.insertOneRow( charaData );

            }
        });

    }

}