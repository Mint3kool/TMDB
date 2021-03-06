package com.shenexample.tay.tmdb.Main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPageAdapter extends FragmentPagerAdapter{

    FragmentManager fragmentManager;

    public MainPageAdapter(FragmentManager fm) {
        super (fm);
        fragmentManager = fm;
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
        Fragment returnFragment;

        switch (position) {
            case 0:
                returnFragment = new HomeFragment();
                break;
            case 1:
                returnFragment = new MainMovieFragment();
                break;
            case 2:
                returnFragment = new MainShowFragment();
                break;
            default:
                returnFragment = new HomeFragment();
                break;
        }
        
        return returnFragment;
    }
}
