package com.example.tranbesample.source;

import com.example.tranbesample.datas.HomeCategory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public interface CategoryDataSource {
    interface LoadCallback{
        void onDataLoaded(List<HomeCategory> categories);
        void onDataNotAvailable();
    }

    void getCategories(@NonNull LoadCallback callback);

    void deleteAllCategories();

    void saveCategory(HomeCategory category);

    void refreshCategories();
}
