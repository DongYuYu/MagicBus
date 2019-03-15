package com.magicbus.data.network;

import com.magicbus.data.entries.LoginResponse;
import com.magicbus.data.entries.PasswordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("registration.php")
    Call<String> register(@Field("firstname") String firstName,
                          @Field("lastname")  String lastName,
                          @Field("address") String address,
                          @Field("email") String email,
                          @Field("mobile") String mobile,
                          @Field("password") String password);

    @GET("/login.php")
    Call<LoginResponse> login(); //implemnt LoginResponse pojo

    @GET("/forgot_pass.php")
    Call<PasswordResponse> forgetPassword();

}
