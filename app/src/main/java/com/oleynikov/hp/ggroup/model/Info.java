package com.oleynikov.hp.ggroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HP on 7/4/2017.
 */

public class Info implements Serializable  {
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("rendered")
    @Expose
    private String rendered;

    public Info(String rendered, String sourceUrl) {
        this.rendered = rendered;
        this.sourceUrl = sourceUrl;
    }

    public Title getTitle() {
        return title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getRendered() {
        return rendered;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }


}
