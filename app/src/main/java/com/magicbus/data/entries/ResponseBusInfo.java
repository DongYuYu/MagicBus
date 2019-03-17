package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseBusInfo {

    @SerializedName("businformation")
    List<BusInformation> BusInfoList = new ArrayList<BusInformation>();

    public List<BusInformation> getBusInfoList() {
        return BusInfoList;
    }

    public void setBusInfoList(List<BusInformation> busInfoList) {
        this.BusInfoList = busInfoList;
    }
}
