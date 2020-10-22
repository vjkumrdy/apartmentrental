package com.findinganapartment.models;

import com.google.gson.annotations.SerializedName;

public class MyRequestPojo {


    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getP_owner() {
        return p_owner;
    }

    public void setP_owner(String p_owner) {
        this.p_owner = p_owner;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBmsg() {
        return bmsg;
    }

    public void setBmsg(String bmsg) {
        this.bmsg = bmsg;
    }

    @SerializedName("bid")
    private String bid;

    @SerializedName("pid")
    private String pid;

    @SerializedName("p_owner")
    private String p_owner;

    @SerializedName("bname")
    private String bname;

    @SerializedName("bmsg")
    private String bmsg;

    @SerializedName("lname")
    private String lname;

    @SerializedName("lmsg")
    private String lmsg;


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLmsg() {
        return lmsg;
    }

    public void setLmsg(String lmsg) {
        this.lmsg = lmsg;
    }
}
