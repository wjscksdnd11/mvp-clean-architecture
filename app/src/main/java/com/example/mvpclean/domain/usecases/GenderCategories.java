package com.example.mvpclean.domain.usecases;

import com.example.mvpclean.Constants;
import com.example.mvpclean.Constants.CATEGORIES_TYPE;
import com.example.mvpclean.datas.HomeCategory;
import com.example.mvpclean.domain.UseCase;
import com.example.mvpclean.domain.filters.CategoryFilter;
import com.example.mvpclean.domain.filters.FilterFactory;
import com.example.mvpclean.source.CategoryDataSource;
import com.example.mvpclean.source.CategoryRepository;

import java.util.List;

import javax.annotation.Nonnull;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class GenderCategories extends UseCase<GenderCategories.RequestValues, GenderCategories.ResponseValue> {

    private final CategoryRepository mCategoryRepository;

    private final FilterFactory mFilterFactory;

    public GenderCategories(@Nonnull CategoryRepository categoryRepository, FilterFactory filterFactory) {
        this.mCategoryRepository = categoryRepository;
        this.mFilterFactory = filterFactory;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        if (requestValues.isForceUpdate()) {
            mCategoryRepository.refreshCategories();
        }

        mCategoryRepository.getCategories(new CategoryDataSource.LoadCallback() {


            @Override
            public void onDataLoaded(List<HomeCategory> categories) {
                CATEGORIES_TYPE currentFiltering = requestValues.getCurrentCategoryType();
                CategoryFilter typeFilter = mFilterFactory.categoryFilterCreate(currentFiltering);
                List<HomeCategory> typeFilteredCategoryList = typeFilter.filter(categories);
                ResponseValue responseValue = new ResponseValue(typeFilteredCategoryList);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final Constants.CATEGORIES_TYPE mCurrentCategoryType;
        private final boolean mForceUpdate;

        public RequestValues(boolean forceUpdate, @NonNull Constants.CATEGORIES_TYPE currentFiltering) {
            mForceUpdate = forceUpdate;
            mCurrentCategoryType = checkNotNull(currentFiltering, "currentFiltering cannot be null!");
        }

        boolean isForceUpdate() {
            return mForceUpdate;
        }

        Constants.CATEGORIES_TYPE getCurrentCategoryType() {
            return mCurrentCategoryType;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<HomeCategory> mCategories;

        ResponseValue(@NonNull List<HomeCategory> categories) {
            mCategories = checkNotNull(categories, "categories cannot be null!");
        }

        public List<HomeCategory> getCategories() {
            return mCategories;
        }
    }
}
