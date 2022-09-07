package com.example.art_stationary.Model;

public class Recyclerhomemodel {
    private String title;
    private String price;
    private String imgid;
    private String id;

    public Recyclerhomemodel(String title, String price, String imgid, String id) {
        this.title = title;
        this.price = price;
        this.imgid = imgid;
        this.id = id;
    }

    public Recyclerhomemodel() {

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

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }


}
