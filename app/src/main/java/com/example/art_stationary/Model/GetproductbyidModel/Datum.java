
package com.example.art_stationary.Model.GetproductbyidModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titlear")
    @Expose
    private String titlear;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("quick")
    @Expose
    private String quick;
    @SerializedName("quickar")
    @Expose
    private String quickar;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("descriptionar")
    @Expose
    private String descriptionar;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("saleprice")
    @Expose
    private String saleprice;
    @SerializedName("sizes")
    @Expose
    private String sizes;
    @SerializedName("colors")
    @Expose
    private String colors;
    @SerializedName("doesvariants")
    @Expose
    private String doesvariants;
    @SerializedName("featured")
    @Expose
    private String featured;
    @SerializedName("hotdeal")
    @Expose
    private String hotdeal;
    @SerializedName("newarrival")
    @Expose
    private String newarrival;
    @SerializedName("similarprods")
    @Expose
    private Object similarprods;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("itemcode")
    @Expose
    private Object itemcode;
    @SerializedName("barcode")
    @Expose
    private Object barcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getQuick() {
        return quick;
    }

    public void setQuick(String quick) {
        this.quick = quick;
    }

    public String getQuickar() {
        return quickar;
    }

    public void setQuickar(String quickar) {
        this.quickar = quickar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionar() {
        return descriptionar;
    }

    public void setDescriptionar(String descriptionar) {
        this.descriptionar = descriptionar;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getDoesvariants() {
        return doesvariants;
    }

    public void setDoesvariants(String doesvariants) {
        this.doesvariants = doesvariants;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getHotdeal() {
        return hotdeal;
    }

    public void setHotdeal(String hotdeal) {
        this.hotdeal = hotdeal;
    }

    public String getNewarrival() {
        return newarrival;
    }

    public void setNewarrival(String newarrival) {
        this.newarrival = newarrival;
    }

    public Object getSimilarprods() {
        return similarprods;
    }

    public void setSimilarprods(Object similarprods) {
        this.similarprods = similarprods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getItemcode() {
        return itemcode;
    }

    public void setItemcode(Object itemcode) {
        this.itemcode = itemcode;
    }

    public Object getBarcode() {
        return barcode;
    }

    public void setBarcode(Object barcode) {
        this.barcode = barcode;
    }

}
