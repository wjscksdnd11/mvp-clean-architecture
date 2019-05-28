package com.example.mvpclean.ui.home;


import com.example.mvpclean.ui.contents.ContentsFragment;
import com.example.mvpclean.ui.empty.BlankFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTabCatgories;

    public HomePagerAdapter(FragmentManager fm, String[] tabCatgories) {
        super(fm);
        this.mTabCatgories = tabCatgories;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 2)
            return ContentsFragment.newInstance();
        else
            return BlankFragment.newInstance();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabCatgories[position];
    }

    @Override
    public int getCount() {
        return mTabCatgories.length;
    }
}
