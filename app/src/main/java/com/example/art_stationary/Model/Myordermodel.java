package com.example.art_stationary.Model;

public class Myordermodel {
    private String Ordernum;
    private String Ammountno;
    private String Date;
    private String Status;

    public Myordermodel(String ordernum, String ammountno, String date, String status) {
        Ordernum = ordernum;
        Ammountno = ammountno;
        Date = date;
        Status = status;
    }

    public String getOrdernum() {
        return Ordernum;
    }

    public void setOrdernum(String ordernum) {
        Ordernum = ordernum;
    }

    public String getAmmountno() {
        return Ammountno;
    }

    public void setAmmountno(String ammountno) {
        Ammountno = ammountno;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
