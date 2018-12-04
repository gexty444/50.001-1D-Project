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
    HashMap<Integer,String> recommendationsPreferences; //store user preferences
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Recommendations> generatedList = new ArrayList<>(); //store generated outfits
    ImageButton closeBtn;
    private String LogCatTAG = "Recommendations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Recommendations","Created RecommendationsActivity");
        setContentView(R.layout.rec_activity_main);

        Intent intent = getIntent();
        String selectedStyle = intent.getStringExtra(MainActivity.selectedStyleKey);

        //TODO: Generate attires based on selectedStyleKey

        mPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);

        generatedList.add(new Recommendations(R.drawable.formal,"Formal Attire " + Integer.toString(generatedList.size()+1)));
        generatedList.add(new Recommendations(R.drawable.business,"Formal Attire " + Integer.toString(generatedList.size()+1)));
        generatedList.add(new Recommendations(R.drawable.menformalred,"Formal Attire " + Integer.toString(generatedList.size()+1)));
        generatedList.add(new Recommendations(R.drawable.menformal,"Formal Attire " + Integer.toString(generatedList.size()+1))); //when there are >3 items, app will crash

        int count =0;
        recommendationsList = new ArrayList<>();
        while(recommendationsList.size()<3 && count< generatedList.size()){
            if(mPreferences.getString("FormalAttire"+Integer.toString(count),"like").equals("like")){
                recommendationsList.add(generatedList.get(count));
            }
            count+=1;
        }

        recAdapter = new RecAdapter(recommendationsList, this);

        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(recAdapter);
        viewPager.setPadding(130,0,130,0);

        final Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);

        recommendationsPreferences = new HashMap<>();

        ImageButton mReject = findViewById(R.id.dislike);
        ImageButton mAccept = findViewById(R.id.like);

        mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                recommendationsPreferences.put(viewPager.getCurrentItem(),"dislike");
                Toast.makeText(getApplicationContext(),"I don't like this",Toast.LENGTH_SHORT).show();
                Log.i("PrefStatus",recommendationsPreferences.toString());
            }
        });
        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                recommendationsPreferences.put(viewPager.getCurrentItem(),"like");
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
            preferencesEditor.putString("FormalAttire"+Integer.toString(i),recommendationsPreferences.get(i));
        }
        preferencesEditor.apply();
    }
}
