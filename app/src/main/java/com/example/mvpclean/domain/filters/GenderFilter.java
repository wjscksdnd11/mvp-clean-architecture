package com.example.mvpclean.domain.filters;

import com.example.mvpclean.Constants;
import com.example.mvpclean.datas.HomeCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * '남성' 탭과 '여성' 탭은 통합할 거라 생각하여 Gender로 포함시켰습니다.
 */
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
