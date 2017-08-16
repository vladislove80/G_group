package com.oleynikov.hp.g_group.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HP on 6/29/2017.
 */

public class Restaurant implements Serializable {
    public Restaurant(String name, String adress, String about, String url,String facebookURL) {
        this.name = name;
        this.adress = adress;
        this.about = about;
        this.url = url;
        this.facebookURL = facebookURL;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("adress")
    @Expose
    private String adress;



    @SerializedName("facebookURL")
    @Expose
    private String facebookURL;


    @SerializedName("about")

    @Expose
    private String about;
    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getAbout() {
        return about;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFacebookURL() {
        return facebookURL;
    }

    public void setFacebookURL(String facebookURL) {
        this.facebookURL = facebookURL;
    }
}
