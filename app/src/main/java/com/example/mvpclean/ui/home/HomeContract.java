package com.example.mvpclean.ui.home;

public interface HomeContract {
    interface Presenter{
        /**
         * data load
         */
        void loadDatas();
    }
    interface View{
        void onLoadedDone();

    }
}
