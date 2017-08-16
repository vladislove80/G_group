
package com.oleynikov.hp.g_group.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Title{

    @SerializedName("rendered")
    @Expose
    private String rendered;
    @SerializedName("featured_media")
    @Expose
    private String featuredMedia;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;

    public String getFeaturedMedia() {
        return featuredMedia;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setFeaturedMedia(String featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

}
