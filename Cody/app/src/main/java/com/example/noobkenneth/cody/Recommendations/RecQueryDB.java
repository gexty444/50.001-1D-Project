package com.example.noobkenneth.cody.Recommendations;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.noobkenneth.cody.R;

import java.util.Random;

public class RecQueryDB {

    String SPACE = " ";
    private String LogCatTAG = "RecommendationsLog";
    Random rand = new Random();
    int randInt = rand.nextInt(3);
    Bitmap bitmap = null;

    public Bitmap queryRandTopFromDB(String selectedStyle) {
        //TODO
        //if (database.top == null) return R.id.transparent;
        //Cursor cursor = readableDb.rawQuery("SELECT * FROM" + SPACE + CharaEntry.TABLE_NAME.TOPS + SPACE + "ORDER BY RANDOM() LIMIT 1";)

        Log.i(LogCatTAG,"queried Top");
        //return getDataFromCursor(?,cursor);
        return bitmap;
    }

    public Bitmap queryRandBottomFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Bottom");

        return bitmap;
    }

    public Bitmap queryRandOverallsFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Overalls");

        return bitmap;
    }

    public Bitmap queryRandShoesFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Shoes");

        return bitmap;
    }

    public Bitmap queryRandBagFromDB(String selectedStyle) {
        //TODO

        Log.i(LogCatTAG,"queried Bag");

        return bitmap;
    }


    public Bitmap queryRandAccessoriesFromDB(String selectedStyle) {
        //TODO

        Log.i(LogCatTAG,"queried accessories");

        return bitmap;
    }

}