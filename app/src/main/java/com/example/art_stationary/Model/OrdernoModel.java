package com.example.art_stationary.Model;

public class OrdernoModel {
    private String description;
    private String price;
    private String quantity;
    private int imgid;

    public OrdernoModel(String description, String price, String quantity, int imgid) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imgid = imgid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
