package com.example.tranbesample.ui.contents;

import com.example.tranbesample.Constants;
import com.example.tranbesample.domain.UseCaseHandler;
import com.example.tranbesample.domain.usecases.GenderCategories;

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
        this.mView.setPresenter(this);

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
    public void loadDatas() {

    }
}
