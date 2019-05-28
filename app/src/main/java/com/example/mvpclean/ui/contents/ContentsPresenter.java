package com.example.mvpclean.ui.contents;

import com.example.mvpclean.Constants;
import com.example.mvpclean.datas.HomeCategory;
import com.example.mvpclean.domain.UseCase;
import com.example.mvpclean.domain.UseCaseHandler;
import com.example.mvpclean.domain.usecases.GenderCategories;

import java.util.List;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ContentsPresenter implements ContentsContract.Presenter {

    private ContentsContract.AdapterView mAdapterView;
    private ContentsContract.AdapterModel mAdapterModel;
    private ContentsContract.ChildAdapterView mAdapterChildView;

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
    public void setAdapterView(@Nonnull ContentsContract.AdapterView adapterView) {
        this.mAdapterView = checkNotNull(adapterView);
    }

    @Override
    public void setAdapterModel(@Nonnull ContentsContract.AdapterModel adapterModel) {
        this.mAdapterModel = checkNotNull(adapterModel);
    }

    @Override
    public void setChildAdapterView(@Nonnull ContentsContract.ChildAdapterView childAdapterView) {
        this.mAdapterChildView = checkNotNull(childAdapterView);
    }

    public void parentItemClick (int adapterPosition) {

        if (mAdapterView!=null){
            HomeCategory category = mAdapterModel.getChildCategories(adapterPosition);
            if (category.childCategories!=null && category.childCategories.size()>0) {
                mAdapterView.onExpandItemView(adapterPosition);
            }else{
                mView.showNoChildCategories();
            }
        }

    }

    @Override
    public void childItemClick(int parentPosition, int childPosition) {

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
    private void processEmptyDatas() {
        mView.showNoActiveDatas();
        mView.setLoadingIndicator(false);

    }
    private void processCategory(List<HomeCategory> categories) {
        if (categories.isEmpty()) {
            processEmptyDatas();
        } else {
            mView.showTasks(categories);
        }
    }
}
