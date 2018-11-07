package com.shenexample.tay.tmdb.Movies;

import org.json.JSONArray;

public interface MovieInterface {
    void getMovies();
    void displayStoredMovies();
    void storeMoviesInDatabase(JSONArray movieList);
}
