package com.example.tranbesample.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tranbesample.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment implements HomeContract.View{
    private SwipeRefreshLayout mSwipeRefresh;
    private HomePresenter mPresenter;

    public static HomeFragment newInstance(){
        Bundle bundle = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        ViewPager pager = view.findViewById(R.id.pager);
        pager.setAdapter(new HomePagerAdapter(getChildFragmentManager(),getResources().getStringArray(R.array.top_category)));
        pager.setCurrentItem(2);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        mPresenter = new HomePresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onLoadedDone() {

    }
}


