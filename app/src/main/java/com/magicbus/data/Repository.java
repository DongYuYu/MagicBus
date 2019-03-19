package com.magicbus.data;

import android.util.Log;

import com.magicbus.data.entries.Coupons;
import com.magicbus.data.entries.CouponsResponse;
import com.magicbus.data.entries.CouponsValidation;
import com.magicbus.data.entries.Login;
import com.magicbus.data.entries.ReserveResponse;
import com.magicbus.data.entries.ServiceListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.magicbus.data.entries.CityResponse;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;
import com.magicbus.reservation.SeatPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository implements DataStructure {

    private static Repository repository;




    Map<Integer, Integer> hash = new HashMap<>();




    String[] seats = {"totalseat",
            "seatone",
            "seattwo",
            "seatthree",
            "seatfour",
            "seatfive",


            "seatsix",
            "seatseven",

            "seateight",
            "seatnine",
            "seatten",
            "seateleven",
            "seattwelve",
            "seatthirteen",
            "seatfourteen",
            "seatfifteen",
            "seatsixteen",
            "seatseventeen",
            "seateighteen",
            "seatnineteen",
            "seattwenty",
            "seattwentyone",
            "seattwentytwo",
            "seattwentythree",
            "seattwentyfour",
            "seattwentyfive",
            "seattwentysix",
            "seattwentyseven",
            "seattwentyeight",

            "seattwentynine",
            "seatthirty",
            "seatthirtyone",
            "seatthirtytwo",
            "seatthirtythree",
            "seatthirtyfour",
            "seatthirtyfive",
            "seatthirtysix",
            "seatthirtyseven",
            "seatthirtyeight",
            "seatthirtynine",
            "seatforty",
            "seatfortyone",
            "seatfortytwo",
            "seatfortythree",
            "seatfortyfour",
            "seatfourtyfive",
            "seatfortysix",
            "seatfourtyseven"};

    {
        for (int i = 0; i < 59; i++) {

            int row = i / 5;

            int col = i % 5;
            if (col == 2) {
                hash.put(i, -1);
            }else if (col < 2) {
                hash.put(i, i - row + 1);
            }else {








                hash.put(i, i - row - 1 + 1);
            }

        }
    }
    ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
    public static Repository getRepository(){
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
                Log.d("ServiceList Response", serviceListResponse.toString());
                callback.serviceListCallback(serviceListResponse.getServiceList());
            }

            @Override
            public void onFailure(Call<ServiceListResponse> call, Throwable t) {
                Log.e("ServiceList Response", t.getMessage());
            }
        });
    }

    public void register(String firstName, String lastName, String address, String email, String mobile, String password, final OnRegisterCallBack onRegisterCallBack) {


        Call<String> register = apiInterface.register(firstName, lastName, address, email, mobile, password);

        register.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String registerResponse = response.body();
                String uri = response.raw().request().url().toString();

                Log.d("Retrofit", registerResponse);

                onRegisterCallBack.onRegisterReceived(registerResponse);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("Retrofit", t.getMessage());
            }
        });
    }

    public void getCities(final OnCityCallBack onCityCallBack) {

        final Call<CityResponse> cityResponseCall = apiInterface.getCity();
            cityResponseCall.enqueue(new Callback<CityResponse>() {
                @Override
                public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                    CityResponse cityResponse = response.body();
                    onCityCallBack.onCityReceived(cityResponse.getCities());
                    Log.d("Retrofit", cityResponse.toString());
                }

                @Override
                public void onFailure(Call<CityResponse> call, Throwable t) {
                    Log.d("Retrofit", t.getMessage());


                }
            });
    }

    public void getCoupons(final CouponsCallback couponsCallback) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<CouponsResponse> couponsList = apiInterface.getCouponsResponse();

        couponsList.enqueue(new Callback<CouponsResponse>() {
            @Override
            public void onResponse(Call<CouponsResponse> call, Response<CouponsResponse> response) {
                CouponsResponse couponsResponse = response.body();
                Log.d("Coupons", couponsResponse.toString());
                couponsCallback.couponsCallback(couponsResponse.getCouponsList());
            }

            @Override
            public void onFailure(Call<CouponsResponse> call, Throwable t) {
                Log.e("Coupons", t.getMessage());
            }
        });
    }

    public void getCouponsValidation(String couponno, final CouponsValidationCallback couponsValidationCallback) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<List<CouponsValidation>> couponsValidation = apiInterface.getCouponsValidationResponse(couponno);

    public void reserve(String busid, List<Integer> selectedSeat, OnReserveCallBack onReserveCallBack) {



        couponsValidation.enqueue(new Callback<List<CouponsValidation>>() {
            @Override
            public void onResponse(Call<List<CouponsValidation>> call, Response<List<CouponsValidation>> response) {
                Log.d("Coupons Validation", response.toString());
                couponsValidationCallback.couponsValidationCallback(response.body());
            }


        int n = selectedSeat.size();
        int[] adjustSeat = new int[n];
        Map<String, String> data = new HashMap<>();

        data.put("busid", busid);

        for (int i = 0; i < n; i++) {



            int adjPostion = hash.get(selectedSeat.get(i));
            adjustSeat[i] = adjPostion;
            data.put(seats[adjPostion], String.valueOf(1));

        }


        Call<ReserveResponse> reserveResponseCall = apiInterface.getReserveResponse(data);
        reserveResponseCall.enqueue(new Callback<ReserveResponse>() {
            @Override
            public void onResponse(Call<ReserveResponse> call, Response<ReserveResponse> response) {
                Log.d("reserved", response.body().getResponses().get(0));
                onReserveCallBack.reserveRecieved(response.body().getResponses().get(0), adjustSeat);
            }

            @Override
            public void onFailure(Call<ReserveResponse> call, Throwable t) {
                Log.d("reserved", t.getMessage());
            }
        });
    }
            @Override
            public void onFailure(Call<List<CouponsValidation>> call, Throwable t) {
                Log.e("Coupons Validation", t.getMessage());
            }
        });


    }
}
