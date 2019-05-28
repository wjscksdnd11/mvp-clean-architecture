package com.example.mvpclean.ui.contents;

import com.example.mvpclean.datas.HomeCategory;

import java.util.List;

interface ContentsContract {

    interface View{
        /**
         * 데이터 로딩 완료
         */
        void onLoadDone();

        /**
         * loading view
         * @param isShow 보이기/숨기기
         */
        void setLoadingIndicator(boolean isShow);

        /**
         * fragment activity에 추가 되었는지 여부
         * @return fragment isAdded()
         */
        boolean isActive();

        /**
         * data 없음
         */
        void showNoActiveDatas();

        /**
         * api callback data error
         */
        void showLoadingTasksError();

        /**
         * response success
         * @param categories main category data
         */
        void showTasks(List<HomeCategory> categories);

        /**
         * child category 가지고 있지 않음
         */
        void showNoChildCategories();
    }

    interface Presenter{

        /**
         * 뷰 관련
         * AdapterView set
         * @param adapterView parent Adpater
         */
        void setAdapterView(AdapterView adapterView);


        /**
         * 데이터관련
         * AdapterModel set
         * @param adapterModel parent Adpater
         */
        void setAdapterModel(AdapterModel adapterModel);

        /**
         * child adapter set
         * @param childAdapterView child adapter
         */
        void setChildAdapterView(ChildAdapterView childAdapterView);


        /**
         * parentItemClick
         * @param position parentApdater position
         */
        void parentItemClick(int position);

        /**
         *
         * @param parentPosition parent category positon
         * @param childPosition child category positon
         */
        void childItemClick(int parentPosition, int childPosition);

        /**
         * data load
         * @param forceUpdate repository에서 cache하고 있는 데이터 날림, 새로 받아옴
         * @param showLoadingUI loading ui 보여지기
         */
        void loadDatas(boolean forceUpdate, boolean showLoadingUI);
    }

    interface AdapterModel{
        /**
         *
         * @param position parent position
         * @return 클릭한 아이템
         */
        HomeCategory getChildCategories(int position);
    }
    interface ChildAdapterView{
        /**
         * 펼치기/접기
         * @param childDatas childData set
         * @param isOpen 열려있음/닫힘
         */
       void toogleView(List<HomeCategory> childDatas, boolean isOpen);
    }

    interface AdapterView{
        /**
         * parent data
         * @param position clicked item position
         */
        void onExpandItemView(int position);
    }
}
