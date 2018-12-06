package com.example.noobkenneth.cody.Recommendations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

public class RecommendationsActivity extends AppCompatActivity {

    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    SharedPreferences mPreferences;
    ViewPager viewPager;
    RecAdapter recAdapter;
    ArrayList<Recommendations> recommendationsList;
    HashMap<int[], String> recommendationsPreferences; //store user preferences
    ArrayList<Recommendations> generatedOutfit = new ArrayList<>(); //store generated outfits
    ImageButton closeBtn;
    String LogCatTAG = "RecommendationsLog";
    RecGenerateOutfit recGenerateOutfit;
    boolean chosen = false;
    Animation scale;
    ImageButton reGenerate;
    int pageChosen;
    int currentPageChosen = -1;
    String selectedStyle = "Casual";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LogCatTAG, "Created RecommendationsActivity");
        setContentView(R.layout.rec_activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        closeBtn = findViewById(R.id.closeRecommendations);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.i(LogCatTAG, "Closed recommendationsActivity");
            }
        });

        recGenerateOutfit = new RecGenerateOutfit(selectedStyle);
        generatedOutfit = recGenerateOutfit.getGeneratedOutfit();

        recAdapter = new RecAdapter(this, selectedStyle);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(recAdapter);
        viewPager.setPadding(130, 0, 130, 0); //the reason we see other cards
        Log.i(LogCatTAG, "finished setting up viewPager!");


        scale = AnimationUtils.loadAnimation(this, R.anim.scale_animation);

        reGenerate = findViewById(R.id.generate);
        reGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //refresh activity upon click
                reGenerate.startAnimation(scale);
                finish();
                overridePendingTransition(0, 0); //so activity won't blink when it's refreshed
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                Toast.makeText(RecommendationsActivity.this, R.string.rec_regenerate, Toast.LENGTH_SHORT);
            }
        });

        final Button chooseOutfit = findViewById(R.id.chooseOutfit);
        final RelativeLayout mainLayout = findViewById(R.id.recommendationsMain);

        chooseOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               currentPageChosen = viewPager.getCurrentItem();
               if (pageChosen == currentPageChosen){ //un-select
                   pageChosen = -1;
                   mainLayout.setBackgroundColor(getResources().getColor(R.color.white));
                   chooseOutfit.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
               }
               else if (pageChosen != currentPageChosen || pageChosen == -1){ //select
                   pageChosen = currentPageChosen;
                   mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                   chooseOutfit.setBackgroundColor(getResources().getColor(R.color.dark_grey));
               }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (pageChosen != viewPager.getCurrentItem()) {
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    chooseOutfit.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                else{
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    chooseOutfit.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
