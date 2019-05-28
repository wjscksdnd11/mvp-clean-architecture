package com.example.mvpclean.domain.filters;

import com.example.mvpclean.datas.HomeCategory;

import java.util.List;
/**
 * HomeCategory 탭의 데이터 가공하는 인터페이스
 */
public interface CategoryFilter {
        List<HomeCategory> filter(List<HomeCategory> tasks);
}
