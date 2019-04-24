package com.example.android.internshalaproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int tabsCount;
    public PageAdapter(FragmentManager fm,int tabsCount) {
        super(fm);
        this.tabsCount=tabsCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new ImageFragment();
            case 1:
                return new SpinnerFragment();
            case 2:
                return new MoviesFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}
