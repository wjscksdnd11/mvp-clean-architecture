package com.example.tranbesample.source.remote;

import com.example.tranbesample.BuildConfig;
import com.example.tranbesample.datas.HomeCategory;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface TrenbeApiService {

    final String base_url = BuildConfig.BASE_URL;

    @GET("Images/categires.json")
    Call<HomeCategory> getCategories();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
