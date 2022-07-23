package com.example.art_stationary.Model;

public class Savedaddressmodel {
    private String textdelivery;
    private String textplace;
    private String textaddress;
    private String textdirection;

    public Savedaddressmodel(String textdelivery, String textplace, String textaddress, String textdirection) {
        this.textdelivery = textdelivery;
        this.textplace = textplace;
        this.textaddress = textaddress;
        this.textdirection = textdirection;
    }

    public String getTextdelivery() {
        return textdelivery;
    }

    public void setTextdelivery(String textdelivery) {
        this.textdelivery = textdelivery;
    }

    public String getTextplace() {
        return textplace;
    }

    public void setTextplace(String textplace) {
        this.textplace = textplace;
    }

    public String getTextaddress() {
        return textaddress;
    }

    public void setTextaddress(String textaddress) {
        this.textaddress = textaddress;
    }

    public String getTextdirection() {
        return textdirection;
    }

    public void setTextdirection(String textdirection) {
        this.textdirection = textdirection;
    }
}
