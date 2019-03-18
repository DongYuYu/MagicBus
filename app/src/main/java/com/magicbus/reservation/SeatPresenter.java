package com.magicbus.reservation;

import android.util.Log;

import com.magicbus.data.entries.ResponseSeatInfo;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatPresenter implements SeatSelectionContract.SeatSelectionPresenter {
    private static final String TAG = SeatPresenter.class.getSimpleName();
    private SeatSelectionContract.SeatSelectionView seatSelectionView;

    public SeatPresenter(SeatSelectionContract.SeatSelectionView seatSelectionView) {
        this.seatSelectionView = seatSelectionView;
    }


    @Override
    public void getSeatInformation(String bus_id) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<ResponseSeatInfo> call = apiInterface.getSeatInfoResponse(bus_id);
        call.enqueue(new Callback<ResponseSeatInfo>() {
            @Override
            public void onResponse(Call<ResponseSeatInfo> call, Response<ResponseSeatInfo> response) {
                Log.d(TAG, " onResponse: " + response.body().getSeatInformationList().get(0));
                seatSelectionView.showSeatInfo(response.body().getSeatInformationList());
            }

            @Override
            public void onFailure(Call<ResponseSeatInfo> call, Throwable t) {
                Log.d(TAG, " onFailure: " + t.getMessage());
            }
        });

    }
}
