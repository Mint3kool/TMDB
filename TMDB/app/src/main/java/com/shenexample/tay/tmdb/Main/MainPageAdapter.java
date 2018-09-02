package com.shenexample.tay.tmdb.Main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shenexample.tay.tmdb.Movies.MovieFragment;
import com.shenexample.tay.tmdb.Movies.PopularMovieFragment;

public class MainPageAdapter extends FragmentPagerAdapter{

    public MainPageAdapter(FragmentManager fm) {
        super (fm);
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
                return "HOME";
        }
    }
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new PopularMovieFragment();
            case 2:
                return new TVFragment();
            default:
                return new HomeFragment();
        }
    }
}
