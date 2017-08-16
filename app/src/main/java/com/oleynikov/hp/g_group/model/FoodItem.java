
package com.oleynikov.hp.g_group.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodItem {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("page")
    @Expose
    private Page page;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("itemFound")
    @Expose
    private Boolean itemFound;
    @SerializedName("categoryFound")
    @Expose
    private Boolean categoryFound;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("type")
    @Expose
    private String type;

    public FoodItem(Boolean success, Page page, List<Item> items, Boolean itemFound, Boolean categoryFound, Object category, String type) {
        this.success = success;
        this.page = page;
        this.items = items;
        this.itemFound = itemFound;
        this.categoryFound = categoryFound;
        this.category = category;
        this.type = type;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getItemFound() {
        return itemFound;
    }

    public void setItemFound(Boolean itemFound) {
        this.itemFound = itemFound;
    }

    public Boolean getCategoryFound() {
        return categoryFound;
    }

    public void setCategoryFound(Boolean categoryFound) {
        this.categoryFound = categoryFound;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
