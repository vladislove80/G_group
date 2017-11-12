
package com.oleynikov.hp.g_group.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("count")
    @Expose
    private int count = 1;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("extra")
    @Expose
    private Boolean extra;
    @SerializedName("types")
    @Expose
    private List<Object> types = null;
    @SerializedName("editLink")
    @Expose
    private Object editLink;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getExtra() {
        return extra;
    }

    public void setExtra(Boolean extra) {
        this.extra = extra;
    }

    public List<Object> getTypes() {
        return types;
    }

    public void setTypes(List<Object> types) {
        this.types = types;
    }

    public Object getEditLink() {
        return editLink;
    }

    public void setEditLink(Object editLink) {
        this.editLink = editLink;
    }

}
