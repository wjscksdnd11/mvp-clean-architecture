package com.example.tranbesample.ui.contents;

interface ContentsContract {

    interface View{
        void onLoadDone();
        void setPresenter(Presenter presenter);
    }

    interface Presenter{
        void setAdapterView(AdapterView adapterView);
        void parentItemClick(int position);
        void loadDatas();
    }

    interface AdapterModel{

    }

    interface AdapterView{
        void onExpandItemView(int position);
    }
}
