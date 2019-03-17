package com.magicbus.data;

import android.util.Log;

import com.magicbus.data.entries.Login;
import com.magicbus.data.entries.ServiceListResponse;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository implements DataStructure {

    private static Repository repository;

    public static Repository getRepository() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void login(String mobile, String password, final LoginCallback callback) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Login>> login = apiInterface.getLogin(mobile, password);

        login.enqueue(new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                if (response != null) {
                    Log.d("Login Response", response.toString());
                    callback.loginCallback(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {
                Log.e("Login Response", t.getMessage());
            }
        });
    }

    public void serviceList(String startpoint_latitude, String startpoint_longitude, String endpoint_latitude, String endpoint_longitude, final ServiceListCallback callback) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<ServiceListResponse> serviceList = apiInterface.getServiceList(startpoint_latitude, startpoint_longitude, endpoint_latitude, endpoint_longitude);

        serviceList.enqueue(new Callback<ServiceListResponse>() {
            @Override
            public void onResponse(Call<ServiceListResponse> call, Response<ServiceListResponse> response) {
                ServiceListResponse serviceListResponse = response.body();
                callback.serviceListCallback(serviceListResponse.getServiceList());
                Log.d("ServiceList Response", serviceListResponse.toString());
            }

            @Override
            public void onFailure(Call<ServiceListResponse> call, Throwable t) {
                Log.e("ServiceList Response", t.getMessage());
            }
        });
    }
}
