package com.example.tranbesample.ui.contents;

import com.example.tranbesample.datas.HomeCategory;

import java.util.List;

interface ContentsContract {

    interface View{
        void onLoadDone();

        void setLoadingIndicator(boolean isShow);

        boolean isActive();

        void showNoActiveTasks();

        void showLoadingTasksError();

        void showTasks(List<HomeCategory> categories);
    }

    interface Presenter{

        void setAdapterView(AdapterView adapterView);

        void parentItemClick(int position);

        void loadDatas(boolean forceUpdate, boolean showLoadingUI);
    }

    interface AdapterModel{

    }

    interface AdapterView{

        void onExpandItemView(int position);
    }
}
