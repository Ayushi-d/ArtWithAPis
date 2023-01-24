package com.example.art_stationary.Model;

public class Recyclerhomemodel {
    private String title;
    private String price;
    private String imgid;
    private String id;
    private String prodid;
    private String tittlear;



    public Recyclerhomemodel(String title, String price, String imgid, String id, String prodid, String tittlear) {
        this.title = title;
        this.price = price;
        this.imgid = imgid;
        this.id = id;
        this.prodid = prodid;
        this.tittlear = tittlear;
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

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }
    public String getTittlear() {
        return tittlear;
    }

    public void setTittlear(String tittlear) {
        this.tittlear = tittlear;
    }
}
