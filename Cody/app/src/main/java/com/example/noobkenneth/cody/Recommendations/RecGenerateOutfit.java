package com.example.noobkenneth.cody.Recommendations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.RecQueryDB;

import java.util.ArrayList;
import java.util.Random;

public class RecGenerateOutfit {

    String selectedStyle = "Casual";
    ArrayList<Recommendations> generatedOutfits = new ArrayList<>();
    private String LogCatTAG = "RecommendationsLog";

    RecQueryDB recQueryDB = new RecQueryDB();

    private Bitmap[] apparelIDs = new Bitmap[6]; //allow us to keep track of the combination of outfits

    Bitmap top;
    Bitmap bottom;
    Bitmap overalls;
    Bitmap shoes;
    Bitmap bag;
    Bitmap accessories;
    Bitmap accessories2;
    Bitmap transparent = null;

    int count;
    Random rand = new Random();
    private int randInt = rand.nextInt(1);

    //get required clothes from database (implement database methods)
    //If there are items in a category, get a random one
    // otherwise, set a transparent image

    //getApparelIDs is used for debugging purposes (to track images generated)
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

    public RecGenerateOutfit(String selectedStyle) {

        if (selectedStyle == "Generate any outfit!") {
            if (randInt == 1) this.selectedStyle = "Casual";
            else this.selectedStyle = "Formal";
        } else this.selectedStyle = selectedStyle;

        top = recQueryDB.queryRandTopFromDB(selectedStyle);
        bottom = recQueryDB.queryRandBottomFromDB(selectedStyle);
        overalls = recQueryDB.queryRandOverallsFromDB(selectedStyle);
        shoes = recQueryDB.queryRandShoesFromDB(selectedStyle);
        bag = recQueryDB.queryRandBagFromDB(selectedStyle);
        accessories = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);
        accessories2 = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);

        count = 0;
        while(count<=2 && accessories.sameAs(accessories2)){
            accessories2 = recQueryDB.queryRandAccessoriesFromDB(selectedStyle);
            count+=1;
            if (count==3 && accessories.sameAs(accessories2)) accessories2 = null;
        }

        generatedOutfits.add(new Recommendations(top));
        generatedOutfits.add(new Recommendations(bottom));
        generatedOutfits.add(new Recommendations(shoes));
        generatedOutfits.add(new Recommendations(bag));
        generatedOutfits.add(new Recommendations(accessories));

        if(count!=3){
            generatedOutfits.add(new Recommendations(accessories2));
            apparelIDs[5] = accessories2;
        }
        else {
            generatedOutfits.add(new Recommendations(transparent));
            apparelIDs[5] = transparent;
        }

        apparelIDs[0] = top;
        apparelIDs[1] = bottom;
        apparelIDs[2] = shoes;
        apparelIDs[3] = bag;
        apparelIDs[4] = accessories;
    }
}