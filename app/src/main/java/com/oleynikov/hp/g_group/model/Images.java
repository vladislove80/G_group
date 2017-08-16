
package com.oleynikov.hp.g_group.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("mobileList")
    @Expose
    private Object mobileList;
    @SerializedName("desktopList")
    @Expose
    private Object desktopList;
    @SerializedName("desktopTablet")
    @Expose
    private Object desktopTablet;
    @SerializedName("big")
    @Expose
    private Object big;

    public Object getMobileList() {
        return mobileList;
    }

    public void setMobileList(Object mobileList) {
        this.mobileList = mobileList;
    }

    public Object getDesktopList() {
        return desktopList;
    }

    public void setDesktopList(Object desktopList) {
        this.desktopList = desktopList;
    }

    public Object getDesktopTablet() {
        return desktopTablet;
    }

    public void setDesktopTablet(Object desktopTablet) {
        this.desktopTablet = desktopTablet;
    }

    public Object getBig() {
        return big;
    }

    public void setBig(Object big) {
        this.big = big;
    }

}
