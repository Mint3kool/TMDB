package com.shenexample.tay.tmdb.Movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenexample.tay.tmdb.R;

import java.util.logging.Logger;

public class PopularMovieFragment extends MovieFragment{

    private TmdbMovieApi api;

    public PopularMovieFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getMovies();
    }

    @Override
    public void DisplayMovies() {
        Log.d("cheese", "yes");
    }

    public void getMovies() {
        api = new TmdbMovieApi(this);
        api.getPopularMovies();
    }
}
