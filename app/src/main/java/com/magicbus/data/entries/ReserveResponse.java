package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReserveResponse {












    @SerializedName("msg")
    List<String> responses = new ArrayList<>();
    public List<String> getResponses() {
        return responses;
    }
}
