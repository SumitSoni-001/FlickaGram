package com.example.flickagram.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.flickagram.API.ApiInterface;
import com.example.flickagram.API.FlickrApi;
import com.example.flickagram.Models.FlickerModel;
import com.example.flickagram.Models.Photo;
import com.example.flickagram.Adapters.ViewPagerAdapter;
import com.example.flickagram.R;
import com.example.flickagram.databinding.ActivityDetailBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    Retrofit retrofit;
    ApiInterface apiInterface;
    List<Photo> itemsList;

    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;

    int position;

    String method = "flickr.interestingness.getList";
    String apiKey = "fc9354ee32bb4738bae51aa9558ceec5";
    String extras = "date_taken,url_h";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        retrofit = FlickrApi.getRETROFIT();
        apiInterface = retrofit.create(ApiInterface.class);
        itemsList = new ArrayList<>();

        // Getting Position of clicked Image
        position = getIntent().getIntExtra("position", 1);

        // Getting Arraylist from FlickerAdapter
//        Bundle bundle = getIntent().getExtras();
//        itemsList.addAll(bundle.getParcelableArrayList("list"));

        getResponse();

    }

    private void getResponse() {

        apiInterface.getResponse(method, apiKey, "json", 1, extras).enqueue(new Callback<FlickerModel>() {
            @Override
            public void onResponse(@NonNull Call<FlickerModel> call, @NonNull Response<FlickerModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        itemsList.addAll(response.body().getPhotos().getPhoto());

                        // Setting ViewPager
                        mViewPager = (ViewPager) findViewById(R.id.viewPagerDetail);
                        mViewPagerAdapter = new ViewPagerAdapter(DetailActivity.this, itemsList, position);
                        mViewPager.setAdapter(mViewPagerAdapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FlickerModel> call, @NonNull Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSplit(this);
    }
}