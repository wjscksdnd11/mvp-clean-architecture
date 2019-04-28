package com.example.tranbesample.ui.contents;

public class ContentsPresenter implements ContentsContract.Presenter {

    private ContentsContract.AdapterView mAdapterView;
    @Override
    public void setAdapterView(ContentsContract.AdapterView adapterView) {
        mAdapterView = adapterView;
    }

    public void parentItemClick (int adapterPosition) {
        if (mAdapterView!=null){
            mAdapterView.onExpandItemView(adapterPosition);
        }

    }
}
