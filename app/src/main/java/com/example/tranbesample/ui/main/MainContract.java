package com.example.tranbesample.ui.main;

interface MainContract {
    interface Presenter{

    }
    interface View{
        /**
         * homeFragment 불러오기
         */
        void goHomeFragment();

        void goEmptyFragment();
    }
}
