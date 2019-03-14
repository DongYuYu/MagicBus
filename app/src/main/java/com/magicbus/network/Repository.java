package com.magicbus.network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements DataStructure{
    private static Repository repository;
    public static Repository getRepository(){
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void register(String firstName, String lastName, String address, String email, String mobile, String password, final OnRegisterCallBack onRegisterCallBack) {


        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        final Call<String> register = apiInterface.register(firstName, lastName, address, email, mobile, password);

        register.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {



                String registerResponse = response.body();


                Log.d("Retrofit", registerResponse);

                onRegisterCallBack.onRegisterReceived(registerResponse);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("Retrofit", t.getMessage());
            }
        });
    }
}
