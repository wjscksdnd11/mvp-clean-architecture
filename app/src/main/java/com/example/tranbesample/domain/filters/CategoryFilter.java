package com.example.tranbesample.domain.filters;

import com.example.tranbesample.datas.HomeCategory;

import java.util.List;

public interface CategoryFilter {
        List<HomeCategory> filter(List<HomeCategory> tasks);
}
