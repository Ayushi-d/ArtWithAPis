package com.example.art_stationary.Model;

public class Recyclerhomemodel {
    private String title;
    private String price;
    private int imgid;

    public Recyclerhomemodel(String title, String price, int imgid) {
        this.title = title;
        this.price = price;
        this.imgid = imgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
