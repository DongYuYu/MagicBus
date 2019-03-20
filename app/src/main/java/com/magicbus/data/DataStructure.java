package com.magicbus.data;

import com.magicbus.data.entries.City;

import java.util.ArrayList;
import com.magicbus.data.entries.Login;
import com.magicbus.data.entries.LoginResponse;
import com.magicbus.data.entries.ServiceList;
import com.magicbus.data.entries.ServiceListResponse;

import java.util.List;

import retrofit2.Response;

public interface DataStructure {
    interface OnRegisterCallBack {
        void onRegisterReceived(String response);
    }

    interface OnCityCallBack {
        void onCityReceived(List<City> cities);
    }

    interface LoginCallback {
        void loginCallback(List<Login> response);
    }

    interface ServiceListCallback {
        void serviceListCallback(List<ServiceList> response);
    }
}
