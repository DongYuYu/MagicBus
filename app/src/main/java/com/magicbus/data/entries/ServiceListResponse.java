package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ServiceListResponse {

    @SerializedName("route")
    List<ServiceList> serviceList = new ArrayList<>();

    public List<ServiceList> getServiceList() {
        return serviceList;
    }
}
