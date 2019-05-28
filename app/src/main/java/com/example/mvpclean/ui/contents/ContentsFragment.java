package com.example.mvpclean.ui.contents;

import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.debug.Injection;
import com.example.mvpclean.R;
import com.example.mvpclean.datas.HomeCategory;

import java.util.ArrayList;
import java.util.List;

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
    private OnFragmentInteractionListener mListener;

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
            mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.loadDatas(true,true);
                }
            });
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onLoadDone() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {
        mLoadingView.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mSwipeRefresh.setRefreshing(isShow);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNoActiveDatas() {
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

    @Override
    public void showNoChildCategories() {
        showSnackBar(getString(R.string.no_child_string));
    }

    private void showSnackBar(String msg){
        if (mListener != null){
            mListener.onFragmentInteractionMsg(msg);
        }
    }
    public interface OnFragmentInteractionListener {

        void onFragmentInteractionMsg(String msg);
    }
}
