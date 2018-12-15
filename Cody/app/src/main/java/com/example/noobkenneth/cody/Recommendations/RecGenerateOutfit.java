package com.example.noobkenneth.cody.Recommendations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.RecQueryDB;

import java.util.ArrayList;
import java.util.Random;


public class RecGenerateOutfit {

    private String selectedStyle = "Casual";
    private ArrayList<Recommendations> generatedOutfits = new ArrayList<>();
    private String LogCatTAG = "RecommendationsLog";

    private RecQueryDB recQueryDB = new RecQueryDB();

    private Bitmap[] apparelIDs = new Bitmap[6];

    private Bitmap top;
    private Bitmap bottom;
    private Bitmap overalls;
    private Bitmap shoes;
    private Bitmap bag;
    private Bitmap accessories;
    private Bitmap accessories2;
    private Bitmap transparent = null;

    private int count;
    private Random rand = new Random();
    private int randInt = rand.nextInt(1);

    //a little convoluted trying to get context and R.string due to the internal calls
    //variables used instead of getting R.string
    private String generate_random = "Generate any outfit!";
    private String casual = "Casual";
    private String formal = "Formal";
    private String smart_casual = "Smart Casual";
    private String business_formal = "Business Formal";

    public Bitmap[] getApparelIDs() {
        return apparelIDs;
    }

    public ArrayList<Recommendations> getGeneratedOutfit() {
        return generatedOutfits;
    }

    public ArrayList<Recommendations> getAnotherOutfit() {
        RecGenerateOutfit anotherOutfit = new RecGenerateOutfit(selectedStyle);
        return anotherOutfit.getGeneratedOutfit();
    }

    //get required clothes from database (implement database methods)
    //If there are items in a category, get a random one
    // otherwise, set a transparent image
    public RecGenerateOutfit(String selectedStyle) {
        // Coded such that there are only two categories
        // (since we only populated database with 2 categories)
        if (selectedStyle.equals(generate_random)) {
            if (randInt == 1) this.selectedStyle = casual;
            else this.selectedStyle = formal;}

        else if (selectedStyle.equals(smart_casual))
            this.selectedStyle = casual;

        else if (selectedStyle.equals(business_formal)){
            this.selectedStyle = formal;
        }
        else {this.selectedStyle = selectedStyle;}

        top = recQueryDB.queryRandTopFromDB(selectedStyle);
        bottom = recQueryDB.queryRandBottomFromDB(selectedStyle);
        overalls = recQueryDB.queryRandOverallsFromDB(selectedStyle);
        shoes = recQueryDB.queryRandShoesFromDB(selectedStyle);
        bag = recQueryDB.queryRandBagFromDB(selectedStyle);
        accessories = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);
        accessories2 = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);

        //Tries to make sure that accessories1 != accessories2,
        //the normal way would be to loop through all accessories in one but that's an O(n^2) comparison
        //just try 3 times and set transparent for accessories if within 3 tries they are still the same
        // unlikely unless there's only one accessory
        count = 0;
        while(count<=2 && (accessories2!=null || accessories!=null)
                && accessories.sameAs(accessories2)){
            accessories2 = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);
            count+=1;
            if (count==3 && accessories.sameAs(accessories2)) accessories2 = null;
        }

        generatedOutfits.add(new Recommendations(top));
        generatedOutfits.add(new Recommendations(bottom));
        generatedOutfits.add(new Recommendations(shoes));
        generatedOutfits.add(new Recommendations(bag));
        generatedOutfits.add(new Recommendations(accessories));
        generatedOutfits.add(new Recommendations(accessories2));


        //set any null getters to transparent; else the example Bitmap will be shown instead
        for (int i =0; i<generatedOutfits.size(); i++){
            if (generatedOutfits.get(i) == null) {
                generatedOutfits.remove(i);
                generatedOutfits.add(i,new Recommendations(transparent));
            }
        }

        apparelIDs[0] = top;
        apparelIDs[1] = bottom;
        apparelIDs[2] = shoes;
        apparelIDs[3] = bag;
        apparelIDs[4] = accessories;
        apparelIDs[5] = accessories2;

    }
}