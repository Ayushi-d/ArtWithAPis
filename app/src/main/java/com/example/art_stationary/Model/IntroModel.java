package com.example.art_stationary.Model;

public class IntroModel {
    private String title;
    private String description;
    private String imageURL;

    public IntroModel(String title, String description, String imageURL) {
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
    }

    public IntroModel() {
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

    public String getImg() {
        return imageURL;
    }

    public void setImg(String imageURL) {
        this.imageURL = imageURL;
    }

}
