package com.example.debug;

import android.content.Context;

import com.example.mvpclean.domain.UseCaseHandler;
import com.example.mvpclean.domain.filters.FilterFactory;
import com.example.mvpclean.domain.usecases.GenderCategories;
import com.example.mvpclean.source.AppDatabase;
import com.example.mvpclean.source.CategoryRepository;
import com.example.mvpclean.source.local.CategoryLocalDataSource;
import com.example.mvpclean.source.remote.CategoryRemoteDataSource;
import com.example.mvpclean.source.remote.ApiService;
import com.example.mvpclean.utils.AppExecutors;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static CategoryRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        AppDatabase database = AppDatabase.getInstance(context);
        return CategoryRepository.getInstance(CategoryRemoteDataSource.getInstance(new AppExecutors(), ApiService.retrofit.create(ApiService.class)),
                CategoryLocalDataSource.getInstance(database.categoryDao()));
    }

    public static GenderCategories provideGenderCategories(@NonNull Context context) {
        return new GenderCategories(provideTasksRepository(context), new FilterFactory());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }


}
