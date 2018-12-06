package com.example.noobkenneth.cody.Recommendations;

import android.database.Cursor;
import android.util.Log;

import com.example.noobkenneth.cody.R;

import java.util.Random;

public class RecQueryDB {

    String SPACE = " ";
    private String LogCatTAG = "RecommendationsLog";
    Random rand = new Random();
    int randInt = rand.nextInt(3);

    public int queryRandTopFromDB(String selectedStyle) {
        //TODO
        //if (database.top == null) return R.id.transparent;
        //Cursor cursor = readableDb.rawQuery("SELECT * FROM" + SPACE + CharaEntry.TABLE_NAME.TOPS + SPACE + "ORDER BY RANDOM() LIMIT 1";)
        switch(randInt){
            case 0: return R.drawable.hoodie;
            case 1: return R.drawable.shirt;
            case 2: return R.drawable.pink_shirt;
        }
        Log.i(LogCatTAG,"queried Top");
        //return getDataFromCursor(?,cursor);
        return R.drawable.tshirt;
    }

    public int queryBottomFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Bottom");

        return R.drawable.jeans;
    }

    public int queryOverallsFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Overalls");

        return R.drawable.formal;
    }

    public int queryShoesFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Shoes");

        return R.drawable.whiteshoes;
    }

    public int queryBagFromDB(String selectedStyle) {
        //TODO

        Log.i(LogCatTAG,"queried Bag");

        return R.drawable.example_bag;
    }


    public int queryAccessoriesFromDB(String selectedStyle) {
        //TODO

        Log.i(LogCatTAG,"queried accessories");

        return R.drawable.cap;
    }

}