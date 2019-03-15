package com.magicbus.network;

import android.util.Log;

import com.magicbus.data.RouteID;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements DataStructure {

    private static Repository repository;

    public static Repository getRepository() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void routeID(String startpoint_latitude, String startpoint_longitude, String endpoint_latitude, String endpoint_longitude, final RouteIDCallback callback) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<List<RouteID>> routeID = apiInterface.getRouteID(startpoint_latitude, startpoint_longitude, endpoint_latitude, endpoint_longitude);

        routeID.enqueue(new Callback<List<RouteID>>() {
            @Override
            public void onResponse(Call<List<RouteID>> call, Response<List<RouteID>> response) {
                Log.d("RouteID Response", response.body().toString());
//                callback.routeIDCallback(response);
            }

            @Override
            public void onFailure(Call<List<RouteID>> call, Throwable t) {
                Log.e("Route ID Response", t.getMessage());
            }
        });
    }
}
