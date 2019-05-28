package com.example.mvpclean.source.remote;

import com.example.mvpclean.BuildConfig;
import com.example.mvpclean.datas.ContainerData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface TrenbeApiService {

    final String base_url = BuildConfig.BASE_URL;

    @GET("Images/categories.json")
    Call<ContainerData> getCategories();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
