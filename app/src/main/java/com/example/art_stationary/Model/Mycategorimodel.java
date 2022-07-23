package com.example.art_stationary.Model;

public class Mycategorimodel {
    private int Ordernum;
    private int Ammountno;
    private int Date;
    private String Status;

    public Mycategorimodel(int ordernum, int ammountno, int date, String status) {
        Ordernum = ordernum;
        Ammountno = ammountno;
        Date = date;
        Status = status;
    }

    public int getOrdernum() {
        return Ordernum;
    }

    public void setOrdernum(int ordernum) {
        Ordernum = ordernum;
    }

    public int getAmmountno() {
        return Ammountno;
    }

    public void setAmmountno(int ammountno) {
        Ammountno = ammountno;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
