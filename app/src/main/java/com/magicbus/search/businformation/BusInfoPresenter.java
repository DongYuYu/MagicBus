package com.magicbus.search.businformation;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.magicbus.data.Repository;
import com.magicbus.data.entries.ResponseBusInfo;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusInfoPresenter implements  BusInfoContract.BusInfoPresenter {
    private static final String TAG = BusInfoPresenter.class.getSimpleName();

    private BusInfoContract.BusInfoView busInfoView;



    private Repository repository;
    public BusInfoPresenter(BusInfoContract.BusInfoView busInfoView){
        this.busInfoView = busInfoView;
    }
    @Override
    public void getBusInfo(String route_id) {


        repository = Repository.getRepository();
        SharedPreferences.Editor editor = repository.getSharePreference().edit();
        editor.putString("route_name", route_id);

        editor.apply();
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<ResponseBusInfo> call = apiInterface.getBusInfoResponse(route_id);
         call.enqueue(new Callback<ResponseBusInfo>() {
             @Override
             public void onResponse(Call<ResponseBusInfo> call, Response<ResponseBusInfo> response) {

                 ResponseBusInfo responseBusInfo = response.body();
                 Log.d(TAG, "onResponse: " + responseBusInfo.getBusInfoList().get(0).getBusdeparturetime());
                 Gson gson = new Gson();
                 String busInfo = gson.toJson(responseBusInfo.getBusInfoList().get(0));





                 editor.putString("busInfo", busInfo).apply();

                 busInfoView.showBusInfo(response.body().getBusInfoList());

             }

             @Override
             public void onFailure(Call<ResponseBusInfo> call, Throwable t) {
                 Log.d(TAG, "onFailure: " + t.getMessage());
             }
         });
    }
}
