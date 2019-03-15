package com.magicbus.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseLogin {

    @SerializedName("CATEGORIES")
    List<Login> loginList = new ArrayList<>();

    public List<Login> getLoginList() {
        return loginList;
    }

    public void setLoginList(List<Login> loginList) {
        this.loginList = loginList;
    }
}
