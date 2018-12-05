package com.example.noobkenneth.cody.Recommendations;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecommendationsActivity extends AppCompatActivity {

    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    SharedPreferences mPreferences;
    ViewPager viewPager;
    RecAdapter recAdapter;
    ArrayList<Recommendations> recommendationsList;
    HashMap<int[],String> recommendationsPreferences; //store user preferences
    ArrayList<Recommendations> generatedOutfit = new ArrayList<>(); //store generated outfits
    ImageButton closeBtn;
    private String LogCatTAG = "RecommendationsLog";
    RecGenerateOutfit generateOutfit;
    boolean chosen = false;
    Animation scale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LogCatTAG, "Created RecommendationsActivity");
        setContentView(R.layout.rec_activity_main);

        Intent intent = getIntent();
        String selectedStyle = intent.getStringExtra(MainActivity.selectedStyleKey);

        closeBtn = findViewById(R.id.closeRecommendations);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.i(LogCatTAG,"Closed recommendationsActivity");
            }
        });

        RecGenerateOutfit recGenerateOutfit = new RecGenerateOutfit(selectedStyle);
        generatedOutfit = recGenerateOutfit.getGeneratedOutfit();
        Log.i(LogCatTAG,"the apparels are "+generatedOutfit.toString());

        recAdapter = new RecAdapter(generatedOutfit, this);
        Log.i(LogCatTAG,"returned from recAdapter");

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(recAdapter);
        viewPager.setPadding(130, 0, 130, 0); //the reason we see other cards
        Log.i(LogCatTAG,"finished setting up viewPager!");

        scale  = AnimationUtils.loadAnimation(this,R.anim.scale_animation);

        final ImageButton reGenerate = findViewById(R.id.generate);
        reGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //refresh activity upon click
                reGenerate.startAnimation(scale);
                finish();
                overridePendingTransition(0, 0); //so activity won't blink when it's refreshed
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        final Button chooseOutfit = findViewById(R.id.chooseOutfit);
        final RelativeLayout mainLayout = findViewById(R.id.recommendationsMain);



        chooseOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chosen) {
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    chooseOutfit.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                    chosen = true;
                }
                else{
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    chooseOutfit.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    chosen = false;
                }
            }
        });
    }

}
