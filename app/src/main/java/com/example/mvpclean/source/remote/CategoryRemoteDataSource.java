package com.example.mvpclean.source.remote;

import android.util.Log;

import com.example.mvpclean.datas.ContainerData;
import com.example.mvpclean.datas.HomeCategory;
import com.example.mvpclean.source.CategoryDataSource;
import com.example.mvpclean.utils.AppExecutors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRemoteDataSource implements CategoryDataSource {

    private ApiService apiService;
    private AppExecutors mAppExcoutors;
    private static CategoryRemoteDataSource INSTANCE;

    private CategoryRemoteDataSource(AppExecutors appExecutors, ApiService apiService) {
        this.apiService = apiService;
        this.mAppExcoutors = appExecutors;

    }
    public static CategoryRemoteDataSource getInstance(AppExecutors appExecutors, ApiService apiService) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRemoteDataSource(appExecutors, apiService);
        }
        return INSTANCE;
    }
    @Override
    public void getCategories(@NonNull final LoadCallback callback) {
        try {
            Call<ContainerData> call = apiService.getCategories();
            call.enqueue(new Callback<ContainerData>() {
            Runnable runnable;
                @Override
                public void onResponse(@Nonnull Call<ContainerData> call, @NonNull final Response<ContainerData> response) {
                   if (response.body()!=null){
                      runnable=  new Runnable(){
                           @Override
                           public void run() {
                               ContainerData containerData = response.body();
                               callback.onDataLoaded( containerData.categoryList);
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
                public void onFailure(@Nullable Call<ContainerData> call,@Nonnull final Throwable t) {
                    mAppExcoutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("error", t.getMessage());
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
