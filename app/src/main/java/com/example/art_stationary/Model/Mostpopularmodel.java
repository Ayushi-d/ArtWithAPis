package com.example.art_stationary.Model;

public class Mostpopularmodel {
    private String title;
    private String price;
    private String imgid;
    private String id;

    public Mostpopularmodel(String title, String price, String imgid) {
        this.title = title;
        this.price = price;
        this.imgid = imgid;
    }

    public Mostpopularmodel(){

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

    public void setid(String imgid) {
        this.id = id;
    }
}
