package com.oleynikov.hp.g_group.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 6/28/2017.
 */

public class Share {
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("title")
    @Expose
    private Title title;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Title getTitle() {
        return title;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

}
