package com.example.dominic.cody;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecommendationsActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Recommendations> recommendationsList;
    HashMap<Integer,String> recommendationsPreferences;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recommendationsList = new ArrayList<>();
        String formalAttireCount = "Formal Attire " + Integer.toString(recommendationsList.size()+1);
        recommendationsList.add(new Recommendations(R.drawable.formal, formalAttireCount));
        recommendationsList.add(new Recommendations(R.drawable.business,formalAttireCount));
        recommendationsList.add(new Recommendations(R.drawable.menformalred,formalAttireCount));
//        recommendationsList.add(new Recommendations(R.drawable.menformal)); //when there are >3 items, app will crash

        adapter = new Adapter(recommendationsList, this);

        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        final Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        recommendationsPreferences = new HashMap<>();


        ImageButton mReject = findViewById(R.id.dislike);
        ImageButton mAccept = findViewById(R.id.like);

        mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                recommendationsPreferences.put(Integer.valueOf(viewPager.getCurrentItem()),"dislike");
                Toast.makeText(getApplicationContext(),"I don't like this",Toast.LENGTH_SHORT).show();
            }
        });
        mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
                recommendationsPreferences.put(Integer.valueOf(viewPager.getCurrentItem()),"like");
                Toast.makeText(getApplicationContext(),"I like this",Toast.LENGTH_SHORT).show();
            }
        });

        //background color
        //right now colors set, in the future can take the color of the outfit/show blurred outfit
        Integer[] formal_colors = {
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.dark_brown),
                getResources().getColor(R.color.wineRed)
//                getResources().getColor(R.color.white)
        };

        colors = formal_colors;

        final RelativeLayout recommendationsMain = findViewById(R.id.recommendationsMain);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length -1)){
                    recommendationsMain.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position+1]
                            ));
                }else{
                    recommendationsMain.setBackgroundColor(colors[colors.length-1]);
                }
            }
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

}
