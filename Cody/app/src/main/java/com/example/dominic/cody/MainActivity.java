package com.example.dominic.cody;

import android.animation.ArgbEvaluator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Recommendations> recommendationsList;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recommendationsList = new ArrayList<>();
//        String formalAttireCount = "Formal Attire " + Integer.toString(recommendationsList.size()+1);
        recommendationsList.add(new Recommendations(R.drawable.formal));
        recommendationsList.add(new Recommendations(R.drawable.business));
//        recommendationsList.add(new Recommendations(R.drawable.menformal)); //when there are >3 items, app will crash
        recommendationsList.add(new Recommendations(R.drawable.menformalred));

        adapter = new Adapter(recommendationsList, this);

        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        //right now colors set, in the future can take the color of the outfit/show blurred outfit
        Integer[] formal_colors = {
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.dark_brown),
                getResources().getColor(R.color.dark_grey)
//                getResources().getColor(R.color.white)
        };

        colors = formal_colors;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length -1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position+1]
                            ));
                }else{
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }
            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }
}
