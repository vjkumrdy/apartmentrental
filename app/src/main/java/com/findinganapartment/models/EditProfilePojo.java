package com.findinganapartment.models;

import com.google.gson.annotations.SerializedName;

public class EditProfilePojo {
    @SerializedName("name")
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhono() {
        return phono;
    }

    public void setPhono(String phono) {
        this.phono = phono;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @SerializedName("email")
    private String email ;

    @SerializedName("phono")
    private String phono ;

    @SerializedName("pwd")
    private String pwd ;


}
