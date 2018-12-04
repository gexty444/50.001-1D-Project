package com.example.noobkenneth.cody.Recommendations;

public class Recommendations {
    private int image;
    private String title = "No Title Set Yet";

    public Recommendations(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public Recommendations(int image) {
        this.image = image;
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


