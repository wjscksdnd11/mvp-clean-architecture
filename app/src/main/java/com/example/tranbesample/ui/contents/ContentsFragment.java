package com.example.tranbesample.ui.contents;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.debug.Injection;
import com.example.tranbesample.R;
import com.example.tranbesample.datas.HomeCategory;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ContentsFragment extends Fragment implements ContentsContract.View {

    private RecyclerView mRecyclerView;
    private ContentsContract.Presenter mPresenter;
    private ProgressBar mLoadingView;
    private SwipeRefreshLayout mSwipeRefresh;
    private ContentsAdapter mAdapter;

    public static ContentsFragment newInstance() {
        Bundle bundle = new Bundle();
        ContentsFragment fragment = new ContentsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public ContentsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contents, container, false);
        mRecyclerView = view.findViewById(R.id.recycler);
        mLoadingView = view.findViewById(R.id.progress);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mPresenter = new ContentsPresenter(
                    this,
                    Injection.provideGenderCategories(getActivity()),
                    Injection.provideUseCaseHandler()
            );
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter = new ContentsAdapter(Glide.with(getActivity()), mPresenter);
            mRecyclerView.setAdapter(mAdapter);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadDatas(true, true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onLoadDone() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {
        mLoadingView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNoActiveTasks() {
        showSnackBar(getString(R.string.empty_string));


    }

    @Override
    public void showLoadingTasksError() {
        showSnackBar(getString(R.string.error_msg));

//        if (getActivity() != null)
//            getActivity().finish();
    }

    @Override
    public void showTasks(List<HomeCategory> categories) {
        mAdapter.replaceData((ArrayList<HomeCategory>) categories);
    }

    private void showSnackBar(String msg){
        if (getView() != null)
            Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
    }

}
