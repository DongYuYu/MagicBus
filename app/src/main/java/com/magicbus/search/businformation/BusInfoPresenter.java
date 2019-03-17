package com.magicbus.search.businformation;

import android.util.Log;

import com.magicbus.data.entries.ResponseBusInfo;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusInfoPresenter implements  BusInfoContract.BusInfoPresenter {
    private static final String TAG = BusInfoPresenter.class.getSimpleName();

    private BusInfoContract.BusInfoView busInfoView;
    public BusInfoPresenter(BusInfoContract.BusInfoView busInfoView){
        this.busInfoView = busInfoView;
    }

    @Override
    public void getBusInfo(String route_id) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<ResponseBusInfo> call = apiInterface.getBusInfoResponse(route_id);

         call.enqueue(new Callback<ResponseBusInfo>() {
             @Override
             public void onResponse(Call<ResponseBusInfo> call, Response<ResponseBusInfo> response) {
                 Log.d(TAG, "onResponse: " + response.body().getBusInfoList().get(0).getBusdeparturetime());
                  busInfoView.showBusInfo(response.body().getBusInfoList());

             }

             @Override
             public void onFailure(Call<ResponseBusInfo> call, Throwable t) {
                 Log.d(TAG, "onFailure: " + t.getMessage());
             }
         });
    }
}
