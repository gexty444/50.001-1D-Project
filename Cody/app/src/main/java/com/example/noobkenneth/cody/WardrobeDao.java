package com.example.noobkenneth.cody;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * This interface accesses the data from the database
 * A mapping of SQL queries to functions
 *
 */
@Dao
public interface WardrobeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WardrobeEntity wardrobeEntity);

    @Query("DELETE FROM WARDROBE_ENTITY")
    void deleteAll();

    @Query("SELECT * FROM WARDROBE_ENTITY ORDER BY id desc")
//  this method is for reading stuff without ViewModel
//  List<WardrobeEntity> getAllClothes();
//  This one reads stuff with view model
    LiveData<List<WardrobeEntity>> getAllClothes();

}
