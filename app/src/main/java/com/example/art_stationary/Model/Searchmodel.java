package com.example.art_stationary.Model;

public class Searchmodel {
    private String description;
    private String price;
    private String imgid;
    private String id;


    public Searchmodel(String description, String price, String imgid) {
        this.description = description;
        this.price = price;
        this.imgid = imgid;
    }

    public Searchmodel() {
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

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
