package com.example.noobkenneth.cody;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Locale;

//Defines sets of related fields to create a table
//table has same name as the class, otherwise change using @Entity(tableName="newName")
//enforce unique key with "indices = {@Index(value = {"id"}, unique = true)}"
// you can also embed java object in a database with @embed

@Entity(tableName = "WARDROBE_ENTITY", indices = {@Index(value = {"id"}, unique = true)})
public class WardrobeEntity {
    @PrimaryKey(autoGenerate = true) //auto generate creates unique key
    @NonNull
    private int id;

//    private Date date_created;
//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    private byte[] image;
//    private String category;
//    private String dress_code;
//    private boolean ootd;


    public int getId() { return id;}
    public void setId(int id) {this.id = id;}
}

