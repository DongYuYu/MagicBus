package com.magicbus.network;

import com.magicbus.data.RouteID;

import java.util.List;

import retrofit2.Response;

public interface DataStructure {

    interface RouteIDCallback {
        void routeIDCallback(Response<List<RouteID>> response);
    }
}
