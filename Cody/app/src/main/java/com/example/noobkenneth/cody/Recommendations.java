package com.example.noobkenneth.cody;

public class Recommendations {
    private int image;
    private String title;

    public Recommendations(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public Recommendations(int image) {
        this.image = image;
        this.title = "Default Title";
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


