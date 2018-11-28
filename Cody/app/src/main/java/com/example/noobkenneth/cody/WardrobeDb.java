package com.example.noobkenneth.cody;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


/**
 * Database layer on top of SQLite database that takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
 * Database holder that serves as an access point to the underlying SQLite database.
 * The Room database uses the DAO to issue queries to the SQLite database.
 */

@Database(entities = {WardrobeEntity.class}, version = 1)
public abstract class WardrobeDb extends RoomDatabase {

    public abstract WardrobeDao wardrobeDao();
}
