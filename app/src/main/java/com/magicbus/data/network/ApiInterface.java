package com.magicbus.data.network;

import com.magicbus.data.entries.Coupons;
import com.magicbus.data.entries.CouponsResponse;
import com.magicbus.data.entries.CouponsValidation;
import com.magicbus.data.entries.Login;
import com.magicbus.data.entries.ReserveResponse;
import com.magicbus.data.entries.ResponseSeatInfo;
import com.magicbus.data.entries.ServiceListResponse;
import com.magicbus.data.entries.Login;

import com.magicbus.data.entries.CityResponse;
import com.magicbus.data.entries.LoginResponse;
import com.magicbus.data.entries.PasswordDetail;
import com.magicbus.data.entries.PasswordResponse;
import com.magicbus.data.entries.ResponseBusInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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


    @GET("registration.php")
    Call<String> register(@Query("firstname") String firstName,
                          @Query("lastname")  String lastName,
                          @Query("address") String address,
                          @Query("email") String email,
                          @Query("mobile") String mobile,
                          @Query("password") String password);

    @GET("/login.php")
    Call<LoginResponse> login(); //implemnt LoginResponse pojo

    @GET("/forgot_pass.php")
    Call<PasswordResponse> forgetPassword();

    @FormUrlEncoded
    @POST("/login.php")
    Call<List<Login>> getLoginResponse(@Field("mobile") String mobile,
                                       @Field("password") String password);

    //@FormUrlEncoded
    //@POST("seatinfo.php")
    //Call<ResponseSeatInfo>  getSeatInfoResponse(@Field("busid") String bus_id);

    @FormUrlEncoded
    @POST("seatinfo.php")
    Observable<ResponseSeatInfo> getSeatInfoResponse(@Field("busid") String bus_id);

    @GET("city.php")
    Call<CityResponse> getCity();

    @FormUrlEncoded
    @POST("forgot_pass.php")
    Call<List<PasswordDetail>> getPasswordDetails(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("businfo.php")
    Call<ResponseBusInfo> getBusInfoResponse(@Field("routeid") String route_id);
    @GET("chooseseat.php")





    Call<ReserveResponse> getReserveResponse(@QueryMap Map<String, String> options);

    @GET("coupon_list.php")
    Call<CouponsResponse> getCouponsResponse();

    @FormUrlEncoded
    @POST("coupon_validation.php")
    Call<List<CouponsValidation>> getCouponsValidationResponse(@Field("couponno") String couponno);

}
