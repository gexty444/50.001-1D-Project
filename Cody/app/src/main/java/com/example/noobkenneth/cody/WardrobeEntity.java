package com.example.noobkenneth.cody;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.Locale;

//Defines sets of related fields to create a table
//table has same name as the class, otherwise change using @Entity(tableName="newName")
//enforce unique key with "indices = {@Index(value = {"id"}, unique = true)}"
// you can also embed java object in a database with @embed

@Entity(tableName = "wardrobe_entity", indices = {@Index(value = {"id"}, unique = true)})
public class WardrobeEntity {
    @PrimaryKey
    private int id;

//    private Date date_created;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    private String category;

    private String dress_code;

    private boolean ootd;


    //getters
    public void setId(int id) {
        this.id = id;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDress_code(String dress_code) {
        this.dress_code = dress_code;
    }

    public void setOotd(boolean ootd) {
        this.ootd = ootd;
    }

    //various getters
    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getDress_code() {
        return dress_code;
    }

    public boolean isOotd() {
        return ootd;
    }
}
