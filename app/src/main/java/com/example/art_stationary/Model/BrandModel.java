package com.example.art_stationary.Model;

public class BrandModel {
    private String imgid;
    private String id;



    public BrandModel(String imgid,String id) {
        this.imgid = imgid;
        this.id = id;
    }

    public BrandModel(){

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
