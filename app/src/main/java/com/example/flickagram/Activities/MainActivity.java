package com.example.flickagram.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.flickagram.API.ApiInterface;
import com.example.flickagram.API.FlickrApi;
import com.example.flickagram.Models.FlickerModel;
import com.example.flickagram.Adapters.FlickerAdapter;
import com.example.flickagram.Models.Photo;
import com.example.flickagram.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    ActivityMainBinding binding;

    Retrofit retrofit;
    ApiInterface apiInterface;
    FlickerAdapter flickerAdapter;
    List<Photo> photoList;

    String method = "flickr.interestingness.getList";
    String apiKey = "fc9354ee32bb4738bae51aa9558ceec5";
    String extras = "date_taken,url_h";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = FlickrApi.getRETROFIT();
        apiInterface = retrofit.create(ApiInterface.class);
        photoList = new ArrayList<>();

        binding.refreshRCV.setOnRefreshListener(this);

        swipeRefresh();

    }

    private void getResponse() {
        binding.refreshRCV.setRefreshing(true);

        apiInterface.getResponse(method, apiKey, "json", 1, extras).enqueue(new Callback<FlickerModel>() {
            @Override
            public void onResponse(@NonNull Call<FlickerModel> call, @NonNull Response<FlickerModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        photoList.addAll(response.body().getPhotos().getPhoto());

                        flickerAdapter = new FlickerAdapter(MainActivity.this, photoList);
                        binding.photosRCV.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        binding.photosRCV.setAdapter(flickerAdapter);
                    }
                        binding.refreshRCV.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<FlickerModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRefresh() {
        getResponse();
    }

    private void swipeRefresh() {
        binding.refreshRCV.post(new Runnable() {
            @Override
            public void run() {
                getResponse();
            }
        });
    }

}