package com.findinganapartment.models;

import com.google.gson.annotations.SerializedName;

public class PropertyPojo {

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getP_area() {
        return p_area;
    }

    public void setP_area(String p_area) {
        this.p_area = p_area;
    }

    public String getP_bath() {
        return p_bath;
    }

    public void setP_bath(String p_bath) {
        this.p_bath = p_bath;
    }

    public String getP_beds() {
        return p_beds;
    }

    public void setP_beds(String p_beds) {
        this.p_beds = p_beds;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_owner() {
        return p_owner;
    }

    public void setP_owner(String p_owner) {
        this.p_owner = p_owner;
    }

    public String getP_pets() {
        return p_pets;
    }

    public void setP_pets(String p_pets) {
        this.p_pets = p_pets;
    }

    public String getP_pic() {
        return p_pic;
    }

    public void setP_pic(String p_pic) {
        this.p_pic = p_pic;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }




    @SerializedName("pid")
    private String pid;

    @SerializedName("p_area")
    private String p_area;

    @SerializedName("p_bath")
    private String p_bath;

    @SerializedName("p_beds")
    private String p_beds;

    @SerializedName("p_name")
    private String p_name;

    @SerializedName("p_owner")
    private String p_owner;

    @SerializedName("p_pets")
    private String p_pets;

    @SerializedName("p_pic")
    private String p_pic;

    @SerializedName("p_price")
    private String p_price;

    @SerializedName("p_type")
    private String p_type;

    @SerializedName("a_status")
    private String a_status;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @SerializedName("location")
    private String location;


    public String getA_status() {
        return a_status;
    }

    public void setA_status(String a_status) {
        this.a_status = a_status;
    }
}
