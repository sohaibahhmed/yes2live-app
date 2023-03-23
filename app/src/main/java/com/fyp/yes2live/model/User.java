package com.fyp.yes2live.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("phonenum")
    public int phonenumber;

    public String getName() {
        return name;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }
}
