package com.magicbus.data;

import com.magicbus.data.entries.City;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public interface DataStructure {
    interface OnRegisterCallBack {
        void onRegisterReceived(String response);



    }






    interface RouteIDCallback {
        void routeIDCallback(Response<List<RouteID>> response);
    }
    interface OnCityCallBack {
        void onCityReceived(List<City> cities);
    }

}
