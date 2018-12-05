package com.example.noobkenneth.cody.Recommendations;

import android.util.Log;

public class Recommendations {
    private int image;
    private String title = "No Title Set Yet";
    String LogCatTag = "RecommendationsLog";
    public Recommendations(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public Recommendations(int image) {
        Log.i(LogCatTag,"Entered Recommendations(int image) constructor");
        this.image = image;
        Log.i(LogCatTag,"Exited Recommendations(int image) constructor");
    }

    public int getImage(){
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }
}


