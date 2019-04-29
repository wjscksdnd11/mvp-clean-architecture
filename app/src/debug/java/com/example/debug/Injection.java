package com.example.debug;

import android.content.Context;

import com.example.tranbesample.domain.UseCaseHandler;
import com.example.tranbesample.domain.filters.FilterFactory;
import com.example.tranbesample.domain.usecases.GenderCategories;
import com.example.tranbesample.source.CategoryRepository;
import com.example.tranbesample.source.TrenbeDatabase;
import com.example.tranbesample.source.local.CategoryLocalDataSource;
import com.example.tranbesample.source.remote.CategoryRemoteDataSource;
import com.example.tranbesample.source.remote.TrenbeApiService;
import com.example.tranbesample.utils.AppExecutors;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static CategoryRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        TrenbeDatabase database = TrenbeDatabase.getInstance(context);
        return CategoryRepository.getInstance(CategoryRemoteDataSource.getInstance(new AppExecutors(), TrenbeApiService.retrofit.create(TrenbeApiService.class)),
                CategoryLocalDataSource.getInstance());
    }

    public static GenderCategories provideGenderCategories(@NonNull Context context) {
        return new GenderCategories(provideTasksRepository(context), new FilterFactory());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }


}
