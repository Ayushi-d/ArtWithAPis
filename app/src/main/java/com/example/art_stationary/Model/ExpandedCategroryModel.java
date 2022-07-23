package com.example.art_stationary.Model;

import java.util.ArrayList;

public class ExpandedCategroryModel {

    private String name;
    private int type;
    private Boolean expand;
    private ArrayList<SubCategoryModel> subCategoryModels;

    public ExpandedCategroryModel() {

    }

    public ExpandedCategroryModel(String name, int type, Boolean expand, ArrayList<SubCategoryModel> subCategoryModels) {
        this.name = name;
        this.type = type;
        this.expand = expand;
        this.subCategoryModels = subCategoryModels;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    public ArrayList<SubCategoryModel> getSubCategoryModels() {
        return subCategoryModels;
    }

    public void setSubCategoryModels(ArrayList<SubCategoryModel> subCategoryModels) {
        this.subCategoryModels = subCategoryModels;
    }
}

