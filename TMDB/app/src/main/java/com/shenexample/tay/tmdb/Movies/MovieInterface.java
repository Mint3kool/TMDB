package com.shenexample.tay.tmdb.Movies;

import com.shenexample.tay.tmdb.Database.MovieRepository;

import org.json.JSONArray;

public interface MovieInterface {

    void displayMovies();
    void getMovies();
    void storeMoviesInDatabase(JSONArray movieArray);
}
