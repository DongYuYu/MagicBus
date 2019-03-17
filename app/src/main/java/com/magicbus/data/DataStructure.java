package com.magicbus.data;

import com.magicbus.data.entries.Login;
import com.magicbus.data.entries.LoginResponse;
import com.magicbus.data.entries.ServiceList;
import com.magicbus.data.entries.ServiceListResponse;

import java.util.List;

import retrofit2.Response;

public interface DataStructure {

    interface LoginCallback {
        void loginCallback(List<Login> response);
    }

    interface ServiceListCallback {
        void serviceListCallback(List<ServiceList> response);
    }
}
