package com.example.noobkenneth.cody.Recommendations;

import android.util.Log;

import com.example.noobkenneth.cody.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.example.noobkenneth.cody.Recommendations.RecQueryDB;

public class RecGenerateOutfit {

    String selectedStyle;
    ArrayList<Recommendations> generatedOutfits = new ArrayList<>();
    private String LogCatTAG = "RecommendationsLog";

    RecQueryDB recQueryDB = new RecQueryDB();

    private int[] apparelIDs = new int[6]; //allow us to keep track of the combination of outfits

    //TODO: get required clothes from database (implement database methods)
    //If there are items in a category, get a random one
    // otherwise, set a transparent image
    // if we can pass a style parameter in the following methods
    // so that it can just be written once at the start, that would be good

    int top = recQueryDB.queryRandTopFromDB(selectedStyle);
    int bottom = recQueryDB.queryBottomFromDB(selectedStyle);
    int overalls = recQueryDB.queryOverallsFromDB(selectedStyle);
    int shoes = recQueryDB.queryShoesFromDB(selectedStyle);
    int bag = recQueryDB.queryBagFromDB(selectedStyle);
    int accessories = recQueryDB.queryAccessoriesFromDB(selectedStyle);
    int accessories2 = recQueryDB.queryAccessoriesFromDB(selectedStyle);

    Random rand = new Random();
    int randInt = rand.nextInt(1);


    public int[] getApparelIDs() {
        return apparelIDs;
    }

    public ArrayList<Recommendations> getGeneratedOutfit() {
        return generatedOutfits;
    }

    public ArrayList<Recommendations> getAnotherOutfit(){
        RecGenerateOutfit anotherOutfit = new RecGenerateOutfit(selectedStyle);
        return anotherOutfit.getGeneratedOutfit();
    }

    private void generateCasual(){
        Log.i(LogCatTAG,"entered generateCasual");

        if (randInt==0){

            generatedOutfits.add(new Recommendations(top));
            generatedOutfits.add(new Recommendations(bottom));

            apparelIDs[0] = top;
            apparelIDs[1] = bottom;
        }

        if (randInt==1){
            generatedOutfits.add(new Recommendations(overalls));
            apparelIDs[0] = overalls;
            generatedOutfits.add(new Recommendations(R.drawable.transparent));
            apparelIDs[1] = 0;
        }

        generatedOutfits.add(new Recommendations(shoes));
        generatedOutfits.add(new Recommendations(bag));
        generatedOutfits.add(new Recommendations(accessories));
        generatedOutfits.add(new Recommendations(R.drawable.transparent));

        apparelIDs[2] = shoes;
        apparelIDs[3] = bag;
        apparelIDs[4] = accessories;
        apparelIDs[5] = accessories2;

    }

    public void generateSmartCasual(){
        //TODO
    }

    public void generateFormal(){
        //TODO
    }

    public void generateBusinessFormal(){
        //TODO
    }



    public RecGenerateOutfit(String selectedStyle) {

        top = recQueryDB.queryRandTopFromDB(selectedStyle);
        bottom = recQueryDB.queryBottomFromDB(selectedStyle);
        overalls = recQueryDB.queryOverallsFromDB(selectedStyle);
        shoes = recQueryDB.queryShoesFromDB(selectedStyle);
        bag = recQueryDB.queryBagFromDB(selectedStyle);
        accessories = recQueryDB.queryAccessoriesFromDB(selectedStyle);
        accessories2 = recQueryDB.queryAccessoriesFromDB(selectedStyle);

        Log.i(LogCatTAG,selectedStyle);
        this.selectedStyle = selectedStyle;
        int randInt = rand.nextInt(1);

        if (selectedStyle.equals("Select Dress Code")){
            Random rand = new Random();
            int randStyle = rand.nextInt(2);
            if(randStyle == 0){generateCasual();}
            if(randStyle == 1){generateFormal();}
        }
        if (selectedStyle.equals("Casual")){
            generateCasual();
        }

        if (selectedStyle.equals("Smart Casual")){
            generateSmartCasual();
        }

        if (selectedStyle.equals("Formal")){
            generateFormal();
        }

        if (selectedStyle.equals("Business Formal")){
            generateBusinessFormal();
        }
    }



    //future implementation: lock certain items and enable re-generation of other apparels
}

