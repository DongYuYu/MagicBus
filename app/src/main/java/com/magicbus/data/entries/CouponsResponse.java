package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CouponsResponse {

    @SerializedName("coupons")
    List<Coupons> couponsList = new ArrayList<>();

    public List<Coupons> getCouponsList() {
        return couponsList;
    }

    public void setCouponsList(List<Coupons> couponsList) {
        this.couponsList = couponsList;
    }
}
