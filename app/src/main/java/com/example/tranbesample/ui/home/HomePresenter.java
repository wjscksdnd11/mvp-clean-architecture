package com.example.tranbesample.ui.home;


import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View mView;

    public HomePresenter(@Nonnull HomeContract.View view) {
        this.mView = checkNotNull(view);
    }

    @Override
    public void loadDatas() {

    }
}
