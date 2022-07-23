package com.example.art_stationary.Model;

public class Cartmodel {
    private String description;
    private String price;
    private int imgid;

    public Cartmodel(String description, String price, int imgid) {
        this.description = description;
        this.price = price;
        this.imgid = imgid;
    }

    public String getTitle() {
        return description;
    }

    public void setTitle(String title) {
        this.description = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
