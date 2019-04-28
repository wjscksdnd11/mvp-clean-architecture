package com.example.tranbesample.ui.home;


class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadDatas() {

    }
}
