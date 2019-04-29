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

        void showNoChildCategories();
    }

    interface Presenter{

        void setAdapterView(AdapterView adapterView);

        void setAdapterModel(AdapterModel adapterModel);

        void setChildAdapterView(ChildAdapterView childAdapterView);

        void parentItemClick(int position);

        void childItemClick(int parentPosition, int childPosition);

        void loadDatas(boolean forceUpdate, boolean showLoadingUI);
    }

    interface AdapterModel{
        HomeCategory getChildCategories(int position);
    }
    interface ChildAdapterView{
       void toogleView(List<HomeCategory> childDatas, boolean isOpen);
    }

    interface AdapterView{

        void onExpandItemView(int position);
    }
}
