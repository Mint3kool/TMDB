package com.shenexample.tay.tmdb.Main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenexample.tay.tmdb.R;
import com.shenexample.tay.tmdb.TV.ShowApi;
import com.shenexample.tay.tmdb.TV.ShowFragment;

import org.json.JSONArray;

public class MainShowFragment extends ShowFragment {

    public static final int MAIN_TV_ID = 2;
    private ShowApi api;

    public MainShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tv, container, false);
        return rootView;
    }

    @Override
    public void getShows() {
        SharedPreferences preferences = getShowPreferences();
        if (!preferences.getBoolean(MainActivity.MAIN_POPULAR_TV_REFRESHED, false)) {
            preferences.edit().putBoolean(MainActivity.MAIN_POPULAR_TV_REFRESHED, true).apply();
            api = new ShowApi(this);
            api.getPopularShows();
        } else {
            displayStoredShows();
        }
    }

    @Override
    public void displayStoredShows() {

    }

    @Override
    public void storeShowsInDatabase(JSONArray movieList) {

    }
}
