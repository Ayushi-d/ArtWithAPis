
package com.example.art_stationary.Model.Categories;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("is_homecategory")
    @Expose
    private String isHomecategory;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titlear")
    @Expose
    private String titlear;
    @SerializedName("subcategories")
    @Expose
    private List<Subcategory> subcategories = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIsHomecategory() {
        return isHomecategory;
    }

    public void setIsHomecategory(String isHomecategory) {
        this.isHomecategory = isHomecategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlear() {
        return titlear;
    }

    public void setTitlear(String titlear) {
        this.titlear = titlear;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

}
