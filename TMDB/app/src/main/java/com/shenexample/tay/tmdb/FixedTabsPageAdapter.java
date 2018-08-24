package com.shenexample.tay.tmdb;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FixedTabsPageAdapter extends FragmentPagerAdapter{

    public FixedTabsPageAdapter(FragmentManager fm) {
        super (fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MovieFragment();
            case 2:
                return new TVFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HOME";
            case 1:
                return "MOVIES";
            case 2:
                return "TV SHOWS";
            default:
                return "oops";
        }
    }
}
