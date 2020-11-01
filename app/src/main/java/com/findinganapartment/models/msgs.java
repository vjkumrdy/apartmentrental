package com.findinganapartment.models;

import com.google.gson.annotations.SerializedName;

public class msgs {

    @SerializedName("message")
    String message;
    @SerializedName("frm")
    String from;
    @SerializedName("eto")
    String to;
    @SerializedName("pid")
    String pid;

    public msgs(String message, String from, String to, String pid) {
        this.message = message;
        this.from = from;
        this.to = to;
        this.pid = pid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
