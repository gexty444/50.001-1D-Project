package com.example.noobkenneth.cody.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.Recommendations.RecAdapter;
import com.example.noobkenneth.cody.Recommendations.RecGenerateOutfit;
import com.example.noobkenneth.cody.Recommendations.Recommendations;
import com.example.noobkenneth.cody.Wardrobe.WardrobeActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class RecommendationsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RecAdapter recAdapter;
    private ArrayList<Recommendations> generatedOutfit = new ArrayList<>(); //store generated outfits
    private String LogCatTAG = "RecommendationsLog";
    private RecGenerateOutfit recGenerateOutfit;
    private Animation scale;
    private ImageButton reGenerate;
    private int pageChosen;
    private int currentPageChosen = -1;
    private int pos;

    static CharaDbHelper charaDbHelper;

    private Intent intent;
    private String SELECTED_STYLE_KEY = "SELECTED_STYLE";
    private String selectedStyle = "Generate any outfit!";
    private String actualSelectedStyle = "Casual";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);

        setContentView(R.layout.rec_activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        intent = new Intent(getApplicationContext(),RecommendationsActivity.class);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            actualSelectedStyle = extras.getString(SELECTED_STYLE_KEY);
            selectedStyle = extras.getString(SELECTED_STYLE_KEY);
            Log.i(LogCatTAG,"extra != null, selectedStyle is " + selectedStyle);

            if (selectedStyle.equals("Smart Casual"))
                this.selectedStyle = "Casual";

            else if (selectedStyle.equals("Business Formal")){
                this.selectedStyle = "Formal";
            }
        }

        //This part dictates the behaviour of the bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        //sets the selected bottom bar item
        bottomNavigationView.setSelectedItemId(R.id.navigation_generate);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Log.i("Logcat", "home pressed from RecommendationsActivity");
                        Intent intent_home = new Intent(RecommendationsActivity.this, MainActivity.class);
                        intent_home.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_home);
                        break;
                    case R.id.navigation_wardrobe:
                        Log.i("Logcat", "wardrobe pressed from MainActivity");
                        Intent intent_wardrobe = new Intent(RecommendationsActivity.this, WardrobeActivity.class);
                        intent_wardrobe.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_wardrobe);
                        break;
                    case R.id.navigation_ootds:
                        Log.i("Logcat", "ootds pressed from MainActivity");
                        Intent intent_ootds = new Intent(RecommendationsActivity.this, CalendarActivity.class);
                        intent_ootds.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_ootds);
                        break;
                    case R.id.navigation_customise:
                        Log.i("Logcat", "customise pressed from MainActivity");
                        Intent intent_customise = new Intent(RecommendationsActivity.this, CustomiseActivity.class);
                        intent_customise.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent_customise);
                        break;
                    case R.id.navigation_generate:
                        Log.i("Logcat", "generate pressed from Generate");
                        break;
                }
                return false;
            }
        });

        // Spinner
        final Spinner recSpinner = (Spinner) findViewById(R.id.recSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dresscode, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recSpinner.setAdapter(adapter);

        if(actualSelectedStyle.equals("Generate any outfit!")) pos = 0;
        else if(actualSelectedStyle.equals("Casual")) pos = 1;
        else if(actualSelectedStyle.equals("Smart Casual")) pos = 2;
        else if(actualSelectedStyle.equals("Formal")) pos = 3;
        else if(actualSelectedStyle.equals("Business Formal")) pos = 4;

        recSpinner.setSelection(pos,false);

        recSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualSelectedStyle = recSpinner.getSelectedItem().toString();
                Log.i(LogCatTAG,actualSelectedStyle);
                if (actualSelectedStyle!= null) intent.putExtra(SELECTED_STYLE_KEY,actualSelectedStyle);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        //the whole section below dictates behaviour of background and button "WEAR THIS"
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
               else if (pageChosen != currentPageChosen){ //select
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
