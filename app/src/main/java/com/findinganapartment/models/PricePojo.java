package com.findinganapartment.models;

import com.google.gson.annotations.SerializedName;

public class PricePojo {
    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    @SerializedName("p_price")
    public String p_price;


}
