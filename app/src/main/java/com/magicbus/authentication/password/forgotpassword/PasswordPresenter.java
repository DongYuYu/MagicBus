package com.magicbus.authentication.password.forgotpassword;


import android.util.Log;

import com.magicbus.data.entries.PasswordDetail;
import com.magicbus.data.network.ApiInterface;
import com.magicbus.data.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordPresenter implements PasswordContract.ForgetPasswordPresenter {

 private static final String TAG = PasswordPresenter.class.getSimpleName();
    private PasswordContract.ForgetPasswordView  passwordView;

    public PasswordPresenter(PasswordContract.ForgetPasswordView passwordView) {
        this.passwordView = passwordView;
    }


    @Override
    public void getPassword(String mobile) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<List<PasswordDetail>> call = apiInterface.getPasswordDetails(mobile);

        call.enqueue(new Callback<List<PasswordDetail>>() {
            @Override
            public void onResponse(Call<List<PasswordDetail>> call, Response<List<PasswordDetail>> response) {
                  Log.d("TAG", "onResponse: " + response.body());
                passwordView.showPassword(response.body());

            }

            @Override
            public void onFailure(Call<List<PasswordDetail>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void returnPassword(List<PasswordDetail> passwordDetailList) {

    }
}
