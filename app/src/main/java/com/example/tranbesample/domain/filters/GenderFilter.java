package com.example.tranbesample.domain.filters;

import com.example.tranbesample.datas.HomeCategory;

import java.util.ArrayList;
import java.util.List;

public class GenderFilter implements CategoryFilter{
    @Override
    public List<HomeCategory> filter(List<HomeCategory> categories) {
        return new ArrayList<>(categories);
    }
}
