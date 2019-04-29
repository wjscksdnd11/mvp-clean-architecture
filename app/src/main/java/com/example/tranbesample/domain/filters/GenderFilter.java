package com.example.tranbesample.domain.filters;

import com.example.tranbesample.Constants;
import com.example.tranbesample.datas.HomeCategory;

import java.util.ArrayList;
import java.util.List;

public class GenderFilter implements CategoryFilter{
    @Override
    public List<HomeCategory> filter(List<HomeCategory> categories) {
        for (HomeCategory category:categories) {
            category.type = Constants.CATEGORY.LARGE_CATEGORY_TYPE;
            for (HomeCategory childCategory: category.childCategories) {
                childCategory.type = Constants.CATEGORY.MID_CATEGORY_TYPE;
            }
        }
        return new ArrayList<>(categories);
    }
}
