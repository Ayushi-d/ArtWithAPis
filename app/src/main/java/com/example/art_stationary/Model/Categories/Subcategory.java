
package com.example.art_stationary.Model.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Subcategory {
    public String id;
    public String parent_id;
    public String title;
    public String titlear;
    public String slug;
    public String description;
    public String sequence;
    public String image;
    public String seo_title;
    public String status;
    public Object display_order;
    public String is_homecategory;
    public ArrayList<Subsubcategory> subsubcategories;


    public Subcategory(String id, String parent_id, String title, String titlear, String slug, String description, String sequence, String image, String seo_title, String status, Object display_order, String is_homecategory, ArrayList<Subsubcategory> subsubcategories) {
        this.id = id;
        this.parent_id = parent_id;
        this.title = title;
        this.titlear = titlear;
        this.slug = slug;
        this.description = description;
        this.sequence = sequence;
        this.image = image;
        this.seo_title = seo_title;
        this.status = status;
        this.display_order = display_order;
        this.is_homecategory = is_homecategory;
        this.subsubcategories = subsubcategories;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSeo_title() {
        return seo_title;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(Object display_order) {
        this.display_order = display_order;
    }

    public String getIs_homecategory() {
        return is_homecategory;
    }

    public void setIs_homecategory(String is_homecategory) {
        this.is_homecategory = is_homecategory;
    }

    public ArrayList<Subsubcategory> getSubsubcategories() {
        return subsubcategories;
    }

    public void setSubsubcategories(ArrayList<Subsubcategory> subsubcategories) {
        this.subsubcategories = subsubcategories;
    }
}
