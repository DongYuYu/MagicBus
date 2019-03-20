package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

public class CouponsValidation {

    @SerializedName("msg")
    private String msg;
    @SerializedName("discount")
    private String discount;

    public CouponsValidation(String msg, String couponno, String discount) {
        this.msg = msg;
        this.discount = discount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String id) {
        this.msg = msg;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
