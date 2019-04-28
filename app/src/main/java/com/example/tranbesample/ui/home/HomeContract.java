package com.example.tranbesample.ui.home;

public interface HomeContract {
    interface Presenter{
        void loadDatas();
    }
    interface View{
        void onLoadedDone();

    }
}
