package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

public class CouponsValidation {

    @SerializedName("id")
    private String id;
    @SerializedName("couponno")
    private String couponno;
    @SerializedName("discount")
    private String discount;

    public CouponsValidation(String id, String couponno, String discount) {
        this.id = id;
        this.couponno = couponno;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCouponno() {
        return couponno;
    }

    public void setCouponno(String couponno) {
        this.couponno = couponno;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
