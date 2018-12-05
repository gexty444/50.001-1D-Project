package com.example.noobkenneth.cody.Recommendations;

import android.util.Log;

public class Recommendations {
    private int image;
    private String title = "No Title Set Yet";
    String LogCatTag = "RecommendationsLog";

    public Recommendations(int image) {
        this.image = image;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}


