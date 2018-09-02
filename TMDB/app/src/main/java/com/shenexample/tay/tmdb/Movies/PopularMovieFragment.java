package com.shenexample.tay.tmdb.Movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenexample.tay.tmdb.R;

public class PopularMovieFragment extends MovieFragment{

    private TmdbMovieApi api;

    public PopularMovieFragment() {
        api = new TmdbMovieApi(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        return rootView;
    }

    public void getMovies() {
        api.getPopularMovies();
    }
}
