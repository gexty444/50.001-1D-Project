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
    private static final int DATABASE_VERSION = 26;
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

    //TODO 7.8 Get the data from cursor
    private CharaData getDataFromCursor(int position, Cursor cursor){

        String name=null;
        String description =null;
        String category =null;
        int formality = 0;
        String last_used = null;
        boolean ootd = false;
        Bitmap bitmap =null;

        cursor.moveToPosition(position);

        int nameIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_NAME);
        name = cursor.getString(nameIndex);

        int descriptionIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_DESCRIPTION);
        description = cursor.getString(descriptionIndex);

        int categoryIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_CATEGORY);
        category = cursor.getString(categoryIndex);

        int formalityIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_FORMALITY);
        formality = Integer.parseInt(cursor.getString(formalityIndex));

        int last_usedIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_LAST_USED);
        last_used = cursor.getString(last_usedIndex);

        int ootdIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_OOTD);
        ootd = Boolean.parseBoolean(cursor.getString(ootdIndex));

        int bitmapIndex = cursor.getColumnIndex(CharaContract.CharaEntry.COL_FILE);
        byte[] bitmapByteArray = cursor.getBlob(bitmapIndex);
        bitmap = BitmapFactory.decodeByteArray(bitmapByteArray,
                0,
                bitmapByteArray.length);

        return new CharaData(name, description, category, formality, last_used, ootd,  bitmap);
    }

    //TODO 7.10 Insert one row when data is passed to it
    public void insertOneRow(CharaData charaData){

        Log.i("Logcat", "CharaDbHelper.insertOneRow  " +
                charaData.getName() + " " + charaData.getDescription() + " " +
                charaData.getCategory() + " " + charaData.getFormality() + " " +
                charaData.getLastUsed() + " " + charaData.getOotd()
        );

        if( writeableDb == null){
            writeableDb = getWritableDatabase();
        }
        ContentValues contentValues
                = new ContentValues();
        contentValues.put(
                CharaContract.CharaEntry.COL_NAME,
                charaData.getName());
        contentValues.put(
                CharaContract.CharaEntry.COL_DESCRIPTION,
                charaData.getDescription());

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
    public int deleteOneRow(String name){
        if( writeableDb == null){
            writeableDb = getWritableDatabase();
        }
        String WHERE_CLAUSE
                = CharaContract.CharaEntry.COL_NAME + " = ?";
        String[] WHERE_ARGS = {name};
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

        private String name;
        private String description;
        private String category;
        private int formality;
        private String last_used;
        private boolean ootd;
        private String file;
        private Bitmap bitmap;

        public CharaData(String name, String description, String file) {
            this.name = name;
            this.description = description;
            this.file = file;
        }

        public CharaData(String name, String description, String category, Bitmap bitmap) {
            this.name = name;
            this.description = description;
            this.category = category;
            this.bitmap = bitmap;
        }

        public CharaData(String name, String description, String category, Integer formality,
                         String last_used, boolean ootd, Bitmap bitmap) {
            Log.i("Logcat", "CharaData constructor called " + name + " " +
                    description + " " +
                    category + " " +
                    formality + " " +
                    last_used + " " +
                    ootd
            );

            this.name = name;
            this.description = description;
            this.category = category;
            this.formality = formality;
            this.last_used = last_used;
            this.ootd = ootd;
            this.bitmap = bitmap;
        }


        public CharaData(String name, String description, Bitmap bitmap){
            this.name = name;
            this.description = description;
            this.bitmap = bitmap;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public String getDescription() {
            return description;
        }

        public String getCategory(){return category;}

        public int getFormality(){return formality;}

        public String getLastUsed(){return last_used;}

        public boolean getOotd(){return ootd;}

        public String getFile() {
            return file;
        }

        public String getName() {
            return name;
        }
    }

}
