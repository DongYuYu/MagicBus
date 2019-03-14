package com.magicbus.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/registration.php")
    Call<String> register();

    @GET("/login.php")
    Call<LoginResponse> login(); //implemnt LoginResponse pojo

    @GET("/forgot_pass.php")
    Call<PasswordResponse> forgetPassword();

}
