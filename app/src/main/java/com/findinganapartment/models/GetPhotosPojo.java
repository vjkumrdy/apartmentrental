package com.findinganapartment.models;

import com.google.gson.annotations.SerializedName;

public class GetPhotosPojo {


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @SerializedName("pid")
    private String pid;

    @SerializedName("photo")
    private String photo;


}
