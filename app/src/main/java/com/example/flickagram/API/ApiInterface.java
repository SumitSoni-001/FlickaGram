package com.example.flickagram.API;

import com.example.flickagram.Models.FlickerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("rest")
    Call<FlickerModel> getResponse(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("format") String format,
            @Query("nojsoncallback") int noJson,
            @Query("extras") String extra);

}
