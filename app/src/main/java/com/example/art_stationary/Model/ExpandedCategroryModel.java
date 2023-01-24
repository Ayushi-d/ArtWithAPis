package com.example.art_stationary.Model;

import java.util.ArrayList;

public class ExpandedCategroryModel {

    private String title;
    private String titlear;
    private String id;
    private String parent_id;
    private ArrayList<SubCategoryModel> subcategories;


    public ExpandedCategroryModel() {

    }

    public ExpandedCategroryModel(String title, String id, String parent_id, ArrayList<SubCategoryModel> subcategories) {
        this.title = title;
        this.id = id;
        this.parent_id = parent_id;
        this.subcategories = subcategories;
    }

    public String getTitlear() {
        return titlear;
    }

    public void setTitlear(String titlear) {
        this.titlear = titlear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public ArrayList<SubCategoryModel> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(ArrayList<SubCategoryModel> subcategories) {
        this.subcategories = subcategories;
    }
}

