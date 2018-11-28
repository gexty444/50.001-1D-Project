package com.example.noobkenneth.cody;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

/**
 * This interface accesses the data from the database
 * A mapping of SQL queries to functions
 *
 */
@Dao
public interface WardrobeDao {

    @Insert
    public void addWardrobeDb(WardrobeEntity wardrobeDb);

}
