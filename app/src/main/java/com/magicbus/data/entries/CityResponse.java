package com.magicbus.data.entries;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CityResponse {

    @SerializedName("city")










    List<City> cities = new ArrayList<>();

    public List<City> getCities() {
        return cities;
    }
}
