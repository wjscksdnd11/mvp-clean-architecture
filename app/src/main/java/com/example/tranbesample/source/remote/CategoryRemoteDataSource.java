package com.example.tranbesample.source.remote;

import com.example.tranbesample.datas.HomeCategory;
import com.example.tranbesample.source.CategoryDataSource;
import com.example.tranbesample.source.entity.Categories;
import com.example.tranbesample.utils.AppExecutors;

import java.util.Collections;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRemoteDataSource implements CategoryDataSource {

    private TrenbeApiService apiService;
    private AppExecutors mAppExcoutors;
    private static CategoryRemoteDataSource INSTANCE;

    private CategoryRemoteDataSource(AppExecutors appExecutors, TrenbeApiService trenbeApiService) {
        this.apiService = trenbeApiService;
        this.mAppExcoutors = appExecutors;

    }
    public static CategoryRemoteDataSource getInstance(AppExecutors appExecutors, TrenbeApiService trenbeApiService) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRemoteDataSource(appExecutors, trenbeApiService);
        }
        return INSTANCE;
    }
    @Override
    public void getCategories(@NonNull final LoadCallback callback) {
        try {
            Call<HomeCategory> call = apiService.getCategories();
            call.enqueue(new Callback<HomeCategory>() {
            Runnable runnable;
                @Override
                public void onResponse(@Nonnull Call<HomeCategory> call, final Response<HomeCategory> response) {
                   if (response.body()!=null){
                      runnable=  new Runnable(){
                           @Override
                           public void run() {
                               callback.onDataLoaded(Collections.singletonList(response.body()));
                           }
                       };
                   }else{
                       runnable= new Runnable(){
                            @Override
                            public void run() {
                                callback.onDataNotAvailable();
                            }
                        };
                   }

                   mAppExcoutors.mainThread().execute(runnable);
                }

                @Override
                public void onFailure(@Nullable Call<HomeCategory> call,@Nonnull Throwable t) {
                    mAppExcoutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onDataNotAvailable();
                        }
                    });
                }

            });


        }catch (Exception e){
            e.printStackTrace();
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void deleteAllCategories() {

    }

    @Override
    public void saveCategory(HomeCategory category) {

    }

    @Override
    public void refreshCategories() {

    }
}
