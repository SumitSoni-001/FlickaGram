package com.example.flickagram.API;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class FlickrApi {

    public static Retrofit RETROFIT = null;

    public static Retrofit getRETROFIT() {

        if (RETROFIT == null) {
            RETROFIT = new Retrofit.Builder()
                    .baseUrl("https://www.flickr.com/services/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return RETROFIT;
    }
}