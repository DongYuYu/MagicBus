package com.magicbus.reservation;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.magicbus.data.DataStructure;
import com.magicbus.data.Repository;
import com.magicbus.data.entries.ResponseSeatInfo;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatPresenter implements SeatSelectionContract.SeatSelectionPresenter, DataStructure.OnReserveCallBack {
    private static final String TAG = SeatPresenter.class.getSimpleName();
    private SeatSelectionContract.SeatSelectionView seatSelectionView;
    private Repository repository;
    public SeatPresenter(SeatSelectionContract.SeatSelectionView seatSelectionView) {
        this.seatSelectionView = seatSelectionView;
        repository = Repository.getRepository();
    }


    @Override
    public void getSeatInformation(String bus_id) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
//        Call<ResponseSeatInfo> call = apiInterface.getSeatInfoResponse(bus_id);
//        call.enqueue(new Callback<ResponseSeatInfo>() {
//            @Override
//            public void onResponse(Call<ResponseSeatInfo> call, Response<ResponseSeatInfo> response) {
//                Log.d(TAG, " onResponse: " + response.body().getSeatInformationList().get(0));
//                seatSelectionView.showSeatInfo(response.body().getSeatInformationList());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseSeatInfo> call, Throwable t) {
//                Log.d(TAG, " onFailure: " + t.getMessage());
//            }
//        });
        Observable<ResponseSeatInfo> seatInfoObservable = apiInterface.getSeatInfoResponse(bus_id);
        seatInfoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);
    }

   @Override
    public void reserve(String busid, List<Integer> selectedSeat) {




        repository.reserve(busid, selectedSeat, this);

    }

    private void handleError(Throwable throwable) {
        Log.d("seat", throwable.getMessage());
    }
    private void handleResult(ResponseSeatInfo responseSeatInfo) {
        Log.d("seat", "");
        seatSelectionView.showSeatInfo(responseSeatInfo.getSeatInformationList());
    }

    @Override
    public void reserveRecieved(String string, int[] adjustSeats) {
        if (string.equals("reserved")) {
            Log.d("reserve", "reserved");


            seatSelectionView.passengerDetails(adjustSeats);

        }
    }
}
