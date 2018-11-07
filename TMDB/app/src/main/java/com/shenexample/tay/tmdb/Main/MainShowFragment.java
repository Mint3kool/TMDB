package com.shenexample.tay.tmdb.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenexample.tay.tmdb.R;
import com.shenexample.tay.tmdb.TV.ShowFragment;

import org.json.JSONArray;

public class MainShowFragment extends ShowFragment {

    public static final int MAIN_TV_ID = 2;

    public MainShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tv, container, false);
        return rootView;
    }

    @Override
    public void getTVShows() {

    }

    @Override
    public void displayStoredTVShows() {

    }

    @Override
    public void storeTVShowsInDatabase(JSONArray movieList) {

    }
}
