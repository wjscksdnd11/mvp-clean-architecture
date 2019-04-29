package com.example.tranbesample.ui.contents;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.tranbesample.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ContentsFragment extends Fragment implements ContentsContract.View{

    private RecyclerView mRecyclerView;
    private ContentsContract.Presenter mPresenter;
    private ProgressBar mLoadingView;
    private SwipeRefreshLayout mSwipeRefresh;

    public static ContentsFragment newInstance() {
        Bundle bundle = new Bundle();
        ContentsFragment fragment = new ContentsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    public ContentsFragment(){

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
        if (getActivity()!=null){
            mRecyclerView.setAdapter(new ContentsAdapter(Glide.with(getActivity()),mPresenter));

        }
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
    public void setPresenter(ContentsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
