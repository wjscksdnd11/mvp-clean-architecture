package com.example.tranbesample.ui.contents;

interface ContentsContract {

    interface View{

    }

    interface Presenter{
        void setAdapterView(AdapterView adapterView);
        void parentItemClick(int position);
    }

    interface AdapterModel{

    }

    interface AdapterView{
        void onExpandItemView(int position);
    }
}
