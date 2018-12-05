package com.example.noobkenneth.cody.Recommendations;

import android.database.Cursor;
import android.util.Log;

import com.example.noobkenneth.cody.R;

public class RecQueryDB {

    String SPACE = " ";
    private String LogCatTAG = "RecommendationsLog";

    public int queryRandTopFromDB(String selectedStyle) {
        //TODO
        //if (database.top == null) return R.id.transparent;
        //Cursor cursor = readableDb.rawQuery("SELECT * FROM" + SPACE + CharaEntry.TABLE_NAME.TOPS + SPACE + "ORDER BY RANDOM() LIMIT 1";)
        Log.i(LogCatTAG,"queried Top");
        return R.drawable.hoodie;
        //return getDataFromCursor(?,cursor);
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