package com.example.noobkenneth.cody;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


/**
 * Database layer on top of SQLite database that takes care of mundane tasks that you used to handle with an SQLiteOpenHelper.
 * Database holder that serves as an access point to the underlying SQLite database.
 * The Room database uses the DAO to issue queries to the SQLite database.
 */

@Database(entities = {WardrobeEntity.class}, version = 1)
public abstract class WardrobeDb extends RoomDatabase {

    public abstract WardrobeDao wardrobeDao();

    //makes the WardrobeDb a Singleton to prevent multiple instances being opened at the same time
    private static volatile WardrobeDb INSTANCE;
    static WardrobeDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WardrobeDb.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WardrobeDb.class, "wardrobe_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
