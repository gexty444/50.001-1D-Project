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
    List<Recommendations> recommendationsList;
    HashMap<int[],String> recommendationsPreferences; //store user preferences
    List<Recommendations> generatedOutfits = new ArrayList<>(); //store generated outfits
    ImageButton closeBtn;
    private String LogCatTAG = "Recommendations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Recommendations", "Created RecommendationsActivity");
        setContentView(R.layout.rec_activity_main);

        Intent intent = getIntent();
        String selectedStyle = intent.getStringExtra(MainActivity.selectedStyleKey);

        closeBtn = findViewById(R.id.closeRecommendations);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //TODO: find out why this has an error :(
        generatedOutfits = RecGenerateOutfits(selectedStyle);
        // Change Hardcoded strings to strings in resources later


        int count = 0;
        recommendationsList = new ArrayList<>();
        while (recommendationsList.size() < 3 && count < generatedOutfits.size()) {
            if (mPreferences.getString("FormalAttire" + Integer.toString(count), "like").equals("like")) {
                recommendationsList.add(generatedOutfits.get(count));
            }
            count += 1;
        }

        recAdapter = new RecAdapter(recommendationsList, this);

        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(recAdapter);
        viewPager.setPadding(130, 0, 130, 0);

        ImageButton reGenerate = findViewById(R.id.generate);
        reGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //refresh activity upon click
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
                mainLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                chooseOutfit.setBackgroundColor(getResources().getColor(R.color.black));
            }
        });
    }
}
        /* Commented out like and dislike functions
        final Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);

        recommendationsPreferences = new HashMap<>();

        ImageButton mReject = findViewById(R.id.dislike);
        ImageButton mAccept = findViewById(R.id.like);

        mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                recommendationsPreferences.put(RecGenerateOutfits.apparelIDs,"dislike");
                Toast.makeText(getApplicationContext(),"I don't like this",Toast.LENGTH_SHORT).show();
                Log.i("PrefStatus",recommendationsPreferences.toString());
            }
        });
        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                recommendationsPreferences.put(RecGenerateOutfits.apparelIDs,"like");
                Toast.makeText(getApplicationContext(),"I like this",Toast.LENGTH_SHORT).show();
                Log.i("PrefStatus",recommendationsPreferences.toString());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        for(int i =0; i<recommendationsPreferences.size(); i++){
            preferencesEditor.putString(recommendationsPreferences.toString(),recommendationsPreferences.get(i));
        }
        preferencesEditor.apply();
    }
}
*/