package com.example.noobkenneth.cody.database;

import android.database.Cursor;
import android.util.Log;

import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.CharaDbHelper;
import com.example.noobkenneth.cody.database.RecommendationsActivity;

import java.util.Random;

public class RecQueryDB {

    String SPACE = " ";
    private String LogCatTAG = "RecommendationsLog";
    Random rand = new Random();
    int randInt = rand.nextInt(3);

    CharaDbHelper.CharaData charaData = null;


    public int queryRandTopFromDB(String selectedStyle) {
        //TODO
        //if (database.top == null) return R.id.transparent;
        //Cursor cursor = readableDb.rawQuery("SELECT * FROM" + SPACE + CharaEntry.TABLE_NAME.TOPS + SPACE + "ORDER BY RANDOM() LIMIT 1";)
        Log.i(LogCatTAG,"queried Top");
        //return getDataFromCursor(?,cursor);

        charaData = RecommendationsActivity.charaDbHelper.queryOneRowRandom("'Tops'");
        Log.i("Logcat", "queryRandTopFromDB: "+ charaData.getCategory());
        return R.drawable.tshirt;
    }

    public int queryBottomFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Bottom");
        charaData = RecommendationsActivity.charaDbHelper.queryOneRowRandom("'Bottoms'");
        return R.drawable.jeans;
    }

    public int queryOverallsFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Overalls");
        charaData = RecommendationsActivity.charaDbHelper.queryOneRowRandom("'One-piece'");
        return R.drawable.formal;
    }

    public int queryShoesFromDB(String selectedStyle) {
        //TODO
        Log.i(LogCatTAG,"queried Shoes");
        charaData = RecommendationsActivity.charaDbHelper.queryOneRowRandom("'Shoes'");
        return R.drawable.whiteshoes;
    }

    public int queryBagFromDB(String selectedStyle) {
        //TODO

        Log.i(LogCatTAG,"queried Bag");
        charaData = RecommendationsActivity.charaDbHelper.queryOneRowRandom("'Bags'");
        return R.drawable.example_bag;
    }


    public int queryAccessoriesFromDB(String selectedStyle) {
        //TODO

        Log.i(LogCatTAG,"queried accessories");
        charaData = RecommendationsActivity.charaDbHelper.queryOneRowRandom("'Accessories'");
        return R.drawable.cap;
    }

}