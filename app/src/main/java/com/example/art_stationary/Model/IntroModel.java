package com.example.art_stationary.Model;

public class IntroModel {
    private String title;
    private String description;
    private int imgid;

    public IntroModel(String title, String description, int imgid) {
        this.title = title;
        this.description = description;
        this.imgid = imgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImg() {
        return imgid;
    }

    public void setImg(int imgid) {
        this.imgid = imgid;
    }

}
