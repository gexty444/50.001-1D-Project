package com.example.noobkenneth.cody.Customise;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.noobkenneth.cody.R;

public class CustomiseActivity extends AppCompatActivity {
    private static final String TAG = "CustomiseActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);
        Log.d(TAG, "onCreate: Starting");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        //Set up ViewPager with sections Adapter
        mViewpager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewpager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewpager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CustomiseFragment(), "Customise");
        adapter.addFragment(new FavouritesFragment(), "Favourites");
        viewPager.setAdapter(adapter);
    }
}