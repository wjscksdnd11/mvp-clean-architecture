package com.example.tranbesample.source.local;

import com.example.tranbesample.datas.HomeCategory;
import com.example.tranbesample.source.CategoryDataSource;

import androidx.annotation.NonNull;

public class CategoryLocalDataSource implements CategoryDataSource {
    private static  CategoryLocalDataSource INSTANCE = null;

    private CategoryLocalDataSource() {
    }

    public static CategoryLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryLocalDataSource();
        }
        return INSTANCE;
    }
    @Override
    public void getCategories(@NonNull LoadCallback callback) {

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
