package com.example.mvpclean.source.local;

import com.example.mvpclean.datas.HomeCategory;
import com.example.mvpclean.source.CategoryDataSource;

import androidx.annotation.NonNull;

public class CategoryLocalDataSource implements CategoryDataSource {
    private static  CategoryLocalDataSource INSTANCE = null;
    private CategoryDao mDao;
    private CategoryLocalDataSource(CategoryDao categoryDao) {
        mDao = categoryDao;
    }

    public static CategoryLocalDataSource getInstance(CategoryDao categoryDao) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryLocalDataSource(categoryDao);
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
