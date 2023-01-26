package com.example.art_stationary.Model;

public class CombinationModel {

    private String colorcode;
    private String colorid;
    private String price;
    private String saleprice;
    private String quantity;
    private String sizename;
    private String sizeid;
    private Boolean selectedColor = false;

    public CombinationModel(String colorcode, String colorid, String price, String saleprice, String quantity, String sizename, String sizeid, Boolean selectedColor) {
        this.colorcode = colorcode;
        this.colorid = colorid;
        this.price = price;
        this.saleprice = saleprice;
        this.quantity = quantity;
        this.sizename = sizename;
        this.sizeid = sizeid;
        this.selectedColor = selectedColor;
    }

    public CombinationModel() {

    }

    public Boolean getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Boolean selectedColor) {
        this.selectedColor = selectedColor;
    }

    public String getColorCode() {
        return colorcode;
    }

    public void setColorCode(String colorcode) {

        this.colorcode = colorcode != "null" ? colorcode : "#FFFFFF";
    }

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSizename() {
        return sizename;
    }

    public void setSizename(String sizename) {
        this.sizename = sizename;
    }

    public String getSizeid() {
        return sizeid;
    }

    public void setSizeid(String sizeid) {
        this.sizeid = sizeid;
    }
}
