package com.shenexample.tay.tmdb.Movies;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieRepository;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface MovieInterface {

//    void processMovies(JSONArray movieArray);
    void getMovies();
    void displayStoredMovies();
    void storeMoviesInDatabase(JSONArray movieList);
}
