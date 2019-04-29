package com.example.tranbesample.ui.contents;

import com.example.tranbesample.Constants;
import com.example.tranbesample.datas.HomeCategory;
import com.example.tranbesample.domain.UseCase;
import com.example.tranbesample.domain.UseCaseHandler;
import com.example.tranbesample.domain.usecases.GenderCategories;

import java.util.List;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ContentsPresenter implements ContentsContract.Presenter {

    private ContentsContract.AdapterView mAdapterView;
    private final ContentsContract.View mView;
    private final GenderCategories mGenderCategories;
    private Constants.CATEGORIES_TYPE mCurrentType = Constants.CATEGORIES_TYPE.WOMEN;
    private boolean mFirstLoad = true;
    private final UseCaseHandler mUsecaseHandler;


    public ContentsPresenter(@Nonnull ContentsContract.View view,
                             @Nonnull GenderCategories genderCategories,
                             @Nonnull UseCaseHandler usecaseHandler) {
        this.mView = checkNotNull(view);
        this.mGenderCategories = checkNotNull(genderCategories);
        this.mUsecaseHandler = checkNotNull(usecaseHandler);


    }

    @Override
    public void setAdapterView(ContentsContract.AdapterView adapterView) {
        mAdapterView = adapterView;
    }

    public void parentItemClick (int adapterPosition) {
        if (mAdapterView!=null){
            mAdapterView.onExpandItemView(adapterPosition);
        }

    }

    @Override
    public void loadDatas(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mView.setLoadingIndicator(true);
        }

        GenderCategories.RequestValues requestValue = new GenderCategories.RequestValues(forceUpdate,
                mCurrentType);

        mUsecaseHandler.execute(mGenderCategories, requestValue,
                new UseCase.UseCaseCallback<GenderCategories.ResponseValue>() {
                    @Override
                    public void onSuccess(GenderCategories.ResponseValue response) {
                        List<HomeCategory> categories = response.getCategories();
                        if (!mView.isActive()) {
                            return;
                        }
                        if (showLoadingUI) {
                            mView.setLoadingIndicator(false);
                        }

                        processCategory(categories);
                    }

                    @Override
                    public void onError() {
                        if (!mView.isActive()) {
                            return;
                        }
                        mView.showLoadingTasksError();
                        mView.setLoadingIndicator(false);
                    }
                });
    }
    private void processEmptyTasks() {
        mView.showNoActiveTasks();
    }
    private void processCategory(List<HomeCategory> categories) {
        if (categories.isEmpty()) {
            processEmptyTasks();
        } else {
            mView.showTasks(categories);
        }
    }
}
