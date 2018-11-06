package com.shenexample.tay.tmdb.Movies;

import org.json.JSONArray;

public class PopularMovieFragment extends MovieFragment{

    private MovieApi api;
    private JSONArray movieArray;

    public PopularMovieFragment() {
        //Required empty public constructor
    }

    public void getMovies() {
        api = new MovieApi(this);
        api.getPopularMovies();
    }

    @Override
    public void displayStoredMovies() {

    }

    @Override
    public void storeMoviesInDatabase(JSONArray movieList) {

    }
}
