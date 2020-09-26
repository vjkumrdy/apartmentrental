package com.findinganapartment.models;

import com.google.gson.annotations.SerializedName;

public class HomePojo {

    @SerializedName("image")
    private String image;

    @SerializedName("price")
    private String price;

    @SerializedName("apart_type")
    private String apart_type;

    @SerializedName("time_spam")
    private String time_spam;

    @SerializedName("bed")
    private String bed;

    @SerializedName("bath")
    private String bath;

    @SerializedName("sq_ft")
    private String sq_ft;

    @SerializedName("pets")
    private String pets;

    @SerializedName("address")
    private String address;

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

    public String getApart_type() {
        return apart_type;
    }

    public void setApart_type(String apart_type) {
        this.apart_type = apart_type;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }

    public String getSq_ft() {
        return sq_ft;
    }

    public void setSq_ft(String sq_ft) {
        this.sq_ft = sq_ft;
    }

    public String getPets() {
        return pets;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime_spam() {
        return time_spam;
    }

    public void setTime_spam(String time_spam) {
        this.time_spam = time_spam;
    }

    public HomePojo(String image, String price, String apart_type, String time_spam, String bed, String bath, String sq_ft, String pets, String address) {
        this.image = image;
        this.price = price;
        this.apart_type = apart_type;
        this.time_spam=time_spam;
        this.bed = bed;
        this.bath = bath;
        this.sq_ft = sq_ft;
        this.pets = pets;
        this.address = address;
    }
}
