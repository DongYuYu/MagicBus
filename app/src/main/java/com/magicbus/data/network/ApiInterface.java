package com.magicbus.data.network;

import com.magicbus.data.entries.Login;
import com.magicbus.data.entries.ServiceListResponse;
import com.magicbus.data.entries.Login;

import com.magicbus.data.entries.CityResponse;
import com.magicbus.data.entries.LoginResponse;
import com.magicbus.data.entries.PasswordDetail;
import com.magicbus.data.entries.PasswordResponse;
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

    @FormUrlEncoded
    @POST("login.php")
    Call<List<Login>> getLogin(@Field("mobile") String mobile,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("routeinfo.php")
    Call<ServiceListResponse> getServiceList(@Field("route-startpoint-latitude") String startpoint_latitude,
                                             @Field("route-startpoint-longitude") String startpoint_longitude,
                                             @Field("route-endpoint-latitude") String endpoint_latitude,
                                             @Field("route-endpoint-longiude") String endpoint_longitude);

//    @GET("/forgot_pass.php")
//    Call<PasswordResponse> forgetPassword();

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

    @FormUrlEncoded
    @POST("/login.php")
    Call<List<Login>> getLoginResponse(@Field("mobile") String mobile,
                                       @Field("password") String password);










    @GET("city.php")
    Call<CityResponse> getCity();
    @FormUrlEncoded
    @POST("forgot_pass.php")
    Call<List<PasswordDetail>> getPasswordDetails(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("businfo.php")
    Call<ResponseBusInfo> getBusInfoResponse(@Field("routeid") String route_id);
}
