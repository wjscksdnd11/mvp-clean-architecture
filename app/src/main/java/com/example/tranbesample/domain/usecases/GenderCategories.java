package com.example.tranbesample.domain.usecases;

import com.example.tranbesample.Constants;
import com.example.tranbesample.Constants.CATEGORIES_TYPE;
import com.example.tranbesample.datas.HomeCategory;
import com.example.tranbesample.domain.UseCase;
import com.example.tranbesample.domain.filters.CategoryFilter;
import com.example.tranbesample.domain.filters.FilterFactory;
import com.example.tranbesample.source.CategoryDataSource;
import com.example.tranbesample.source.CategoryRepository;

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
                CategoryFilter taskFilter = mFilterFactory.categoryFilterCreate(currentFiltering);
                List<HomeCategory> tasksFiltered = taskFilter.filter(categories);
                ResponseValue responseValue = new ResponseValue(tasksFiltered);
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

        public boolean isForceUpdate() {
            return mForceUpdate;
        }

        public Constants.CATEGORIES_TYPE getCurrentCategoryType() {
            return mCurrentCategoryType;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<HomeCategory> mCategories;

        public ResponseValue(@NonNull List<HomeCategory> categories) {
            mCategories = checkNotNull(categories, "categories cannot be null!");
        }

        public List<HomeCategory> getCategories() {
            return mCategories;
        }
    }
}
