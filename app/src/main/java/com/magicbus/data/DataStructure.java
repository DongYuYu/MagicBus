package com.magicbus.data;

import com.magicbus.data.entries.City;

import java.util.ArrayList;
import java.util.List;

public interface DataStructure {
    interface OnRegisterCallBack {
        void onRegisterReceived(String response);



    }







    interface OnCityCallBack {
        void onCityReceived(List<City> cities);
    }
}
