package com.magicbus.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * this is a retrofit instance
 */
public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASEURL = "http://rjtmobile.com/aamir/otr/android-app/";
    public static Retrofit getRetrofitInstance() {
        /**
         * one retroit instance is a builder design pattern which uses url
         * to parse response from server
         *
         * the whole is facsade desgin pattern because it
         */
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())





                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .build();
        }
        return retrofit;
    }
}
