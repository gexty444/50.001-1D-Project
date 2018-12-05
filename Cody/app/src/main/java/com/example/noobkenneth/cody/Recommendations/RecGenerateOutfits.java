package com.example.noobkenneth.cody.Recommendations;

import com.example.noobkenneth.cody.R;

import java.util.List;
import java.util.Random;

public class RecGenerateOutfits {

    String selectedStyle;
    List<Recommendations> generatedOutfits;

    public static int[] apparelIDs = new int[6]; //allow us to keep track of the combination of outfits

    //TODO: get required clothes from database (implement database methods)
    //If there are items in a category, get a random one
    // otherwise, set a transparent image
    // if we can pass a style parameter in the following methods
    // so that it can just be written once at the start, that would be good

    int top = getRandTopFromDB(selectedStyle);
    int bottom = getBottomFromDB();
    int overalls = getOverallsFromDB();
    int shoes = getShoesFromDB();
    int bag = getBagFromDB();
    int accessories = getAccessoriesFromDB();
    int accessories2 = getAccessoriesFromDB();

    Random rand = new Random();
    int randInt = rand.nextInt(1);

    /*
    public int[] getApparelIDs() {
        return apparelIDs;
    }*/

    public List<Recommendations> generateCasual(){
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
        generatedOutfits.add(new Recommendations(accessories2));

        apparelIDs[2] = shoes;
        apparelIDs[3] = bag;
        apparelIDs[4] = accessories;
        apparelIDs[5] = accessories2;


        return generatedOutfits;
    }

    public RecGenerateOutfits(String selectedStyle) {
        this.selectedStyle = selectedStyle;
        if (selectedStyle.equals("Casual")){
            generateCasual();
        }

        if (selectedStyle.equals("Smart Casual")){}

        if (selectedStyle.equals("Formal")){}

        if (selectedStyle.equals("Business Formal")){}


    }



    //future implementation: lock certain items and enable re-generation of other apparels
}

