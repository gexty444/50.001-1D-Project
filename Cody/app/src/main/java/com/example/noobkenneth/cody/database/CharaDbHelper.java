package com.example.noobkenneth.cody.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.noobkenneth.cody.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by norman_lee on 6/10/17.
 */

public class CharaDbHelper extends SQLiteOpenHelper {


    private final Context context;
    private static String PACKAGE_NAME;
    private static final int DATABASE_VERSION = 29;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteDatabase readableDb;
    private SQLiteDatabase writeableDb;
    private static CharaDbHelper charaDbHelper;

    //TODO 7.4 Create the Constructor and make it a singleton
    private CharaDbHelper(Context context){
        super(context, CharaContract.CharaEntry.TABLE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    static CharaDbHelper createCharaDbHelper(Context context) {
        if( charaDbHelper == null){
            charaDbHelper = new CharaDbHelper(context.getApplicationContext());
        }
        return charaDbHelper;
    }

    //TODO 7.5 Complete onCreate. You may make use of fillTable below
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CharaContract.CharaSql.SQL_CREATE_TABLE);
//        fillTable(sqLiteDatabase);

    }

    //TODO 7.6 Complete onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CharaContract.CharaSql.SQL_DROP_TABLE);
        onCreate(sqLiteDatabase);
    }


    //TODO 7.8 query one row at random
    public CharaData queryOneRowRandom(){
        if( readableDb == null){
            readableDb = getReadableDatabase();
        }
        Cursor cursor = readableDb.rawQuery(
                CharaContract.CharaSql.SQL_QUERY_ONE_RANDOM_ROW,
                null);

        return getDataFromCursor(0, cursor);

    }

    //TODO 7.9 queryOneRow gets the entire database
    // TODO and returns the row in position as a CharaData object
    public CharaData queryOneRow(int position){
        if( readableDb == null){
            readableDb = getReadableDatabase();
        }
        Cursor cursor = readableDb.rawQuery(
                CharaContract.CharaSql.SQL_QUERY_ALL_ROWS,
                null);

        return getDataFromCursor(position, cursor);
    }

    public CharaData queryOneRowClothes(int position){
        if( readableDb == null){
            readableDb = getReadableDatabase();
        }
        Cursor cursor = readableDb.rawQuery(
                CharaContract.CharaSql.SQL_QUERY_OOTD,
                new String[]{"0"});
        return getDataFromCursor(position, cursor);
    }

    public CharaData queryOneRowOotd(int position){
        if( readableDb == null){
            readableDb = getReadableDatabase();
        }
        Cursor cursor = readableDb.rawQuery(
                CharaContract.CharaSql.SQL_QUERY_OOTD,
                new String[]{"1"});
        return getDataFromCursor(position, cursor);
    }

    //TODO 7.8 Get the data from cursor
    private CharaData getDataFromCursor(int position, Cursor cursor){

        int id = 0;
        String category =null;
        String formality = null;
        String last_used = null;
        boolean ootd = false;
        Bitmap bitmap =null;

        cursor.moveToPosition(position);

        int idIndex = cursor.getColumnIndex(CharaContract.CharaEntry._ID);
        id = Integer.parseInt(cursor.getString(idIndex));
        Log.i("Logcat", "getDataFromCursor: id: " + id);

        int categoryIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_CATEGORY);
        category = cursor.getString(categoryIndex);

        int formalityIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_FORMALITY);
        formality = cursor.getString(formalityIndex);

        int last_usedIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_LAST_USED);
        last_used = cursor.getString(last_usedIndex);

        int ootdIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_OOTD);
        ootd = Boolean.parseBoolean(cursor.getString(ootdIndex));

        int bitmapIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_FILE);
        byte[] bitmapByteArray = cursor.getBlob(bitmapIndex);
        bitmap = BitmapFactory.decodeByteArray(bitmapByteArray,
                0,
                bitmapByteArray.length);

        return new CharaData(id, category, formality, last_used, ootd,  bitmap);
    }

    //TODO 7.10 Insert one row when data is passed to it
    public void insertOneRow(CharaData charaData){

        Log.i("Logcat", "CharaDbHelper.insertOneRow  " +
//                charaData.getName() + " " + charaData.getDescription() + " " +
                charaData.getCategory() + " " + charaData.getFormality() + " " +
                charaData.getLastUsed() + " " + charaData.getOotd()
        );

        if( writeableDb == null){
            writeableDb = getWritableDatabase();
        }
        ContentValues contentValues
                = new ContentValues();

        contentValues.put(
                CharaContract.CharaEntry.COL_CATEGORY,
                charaData.getCategory());

        contentValues.put(
                CharaContract.CharaEntry.COL_FORMALITY,
                charaData.getFormality());

        contentValues.put(
                CharaContract.CharaEntry.COL_LAST_USED,
                charaData.getLastUsed());

        contentValues.put(
                CharaContract.CharaEntry.COL_OOTD,
                charaData.getOotd());

        byte[] bitmapdata = Utils.convertBitmapToByteArray(
                charaData.getBitmap());
        contentValues.put(
                CharaContract.CharaEntry.COL_FILE,
                bitmapdata);
        writeableDb.insert(CharaContract.CharaEntry.TABLE_NAME,
                null, contentValues);
    }


    //TODO 7.11 Delete one row given the name field
    public int deleteOneRow(String id){
        if( writeableDb == null){
            writeableDb = getWritableDatabase();
        }
        String WHERE_CLAUSE
                = CharaContract.CharaEntry._ID + " = ?";
        String[] WHERE_ARGS = {id};
        int rowsDeleted = writeableDb.delete(CharaContract.CharaEntry.TABLE_NAME,
                WHERE_CLAUSE, WHERE_ARGS);
        Log.i("Logcat", "rows deleted: " + rowsDeleted);
        return rowsDeleted;
    }

    //TODO 7.7 return the number of rows in the database
    public long queryNumRows(){
        if( readableDb == null){
            readableDb = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(readableDb,
                CharaContract.CharaEntry.TABLE_NAME);
    }

    public Context getContext(){
        return context;
    }


    //TODO 7.3 Create a model class to represent our data
    static class CharaData{

        private int id;
        private String category;
        private String formality;
        private String last_used;
        private boolean ootd;
        private String file;
        private Bitmap bitmap;

        public CharaData(String category, String formality,
                         String last_used, boolean ootd, Bitmap bitmap) {
            Log.i("Logcat", "CharaData constructor called " +
                    category + " " +
                    formality + " " +
                    last_used + " " +
                    ootd
            );
            this.category = category;
            this.formality = formality;
            this.last_used = last_used;
            this.ootd = ootd;
            this.bitmap = bitmap;
        }

        public CharaData(int id, String category, String formality,
                         String last_used, boolean ootd, Bitmap bitmap) {
            Log.i("Logcat", "CharaData constructor called " +
                    id + " " +
                    category + " " +
                    formality + " " +
                    last_used + " " +
                    ootd
            );
            this.id = id;
            this.category = category;
            this.formality = formality;
            this.last_used = last_used;
            this.ootd = ootd;
            this.bitmap = bitmap;
        }


        public Bitmap getBitmap() {
            return bitmap;
        }

        public String getCategory(){return category;}

        public String getFormality(){return formality;}

        public String getLastUsed(){return last_used;}

        public boolean getOotd(){return ootd;}

        public String getFile() {
            return file;
        }

        public int getId() {
            Log.i("Logcat", "getId() with value of: " + id);
            return id;}


    }

}
