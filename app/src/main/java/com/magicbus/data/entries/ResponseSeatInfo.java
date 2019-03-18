package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ResponseSeatInfo {

    @SerializedName("seatinformation")
    List<SeatInformation> seatInformationList = new ArrayList<SeatInformation>();

    public List<SeatInformation> getSeatInformationList() {
        return seatInformationList;
    }

    public void setSeatInformationList(List<SeatInformation> seatInformationList) {
        this.seatInformationList = seatInformationList;
    }




}
