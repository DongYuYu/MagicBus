package com.magicbus.network;

import com.magicbus.data.entries.PasswordDetail;
import com.magicbus.data.entries.ResponseBusInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/registration.php")
    Call<String> register();

    // LoginResponse is the Module used for login
    /*@GET("/login.php")
    Call<LoginResponse> login(); *///implemnt LoginResponse pojo


    @FormUrlEncoded
    @POST("forgot_pass.php")
    Call<List<PasswordDetail>> getPasswordDetails(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("businfo.php")
    Call<ResponseBusInfo> getBusInfoResponse(@Field("routeid") String route_id);

}
