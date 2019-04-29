package com.example.tranbesample.domain.filters;

import com.example.tranbesample.Constants;

import java.util.HashMap;
import java.util.Map;

public class FilterFactory {

        private static final Map<Constants.CATEGORIES_TYPE, CategoryFilter> mFilters = new HashMap<>();

        public FilterFactory() {
//            mFilters.put(Constants.CATEGORIES_TYPE.HOME, new HomeFilter());
//            mFilters.put(Constants.CATEGORIES_TYPE.BRAND, new BrandFilter());
            mFilters.put(Constants.CATEGORIES_TYPE.WOMEN, new GenderFilter());
            mFilters.put(Constants.CATEGORIES_TYPE.MAN, new GenderFilter());
//            mFilters.put(Constants.CATEGORIES_TYPE.SALE, new SaleFilter());
        }

        public CategoryFilter categoryFilterCreate(Constants.CATEGORIES_TYPE filterType) {
            return mFilters.get(filterType);
        }
}
