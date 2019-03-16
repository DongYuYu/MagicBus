package com.magicbus.data.entries;


import com.google.gson.annotations.SerializedName;

public class PasswordDetail {

    public PasswordDetail(String msg, String mobile, String password) {
        this.msg = msg;
        this.mobile = mobile;
        this.password = password;
    }

    @SerializedName("msg")
    private String msg;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("password")
    private String password;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
