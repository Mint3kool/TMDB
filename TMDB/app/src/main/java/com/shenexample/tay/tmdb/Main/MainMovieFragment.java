package com.shenexample.tay.tmdb.Main;

import android.content.Context;
import android.content.SharedPreferences;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieSorter;
import com.shenexample.tay.tmdb.Movies.MovieAdapter;
import com.shenexample.tay.tmdb.Movies.MovieFragment;
import com.shenexample.tay.tmdb.Movies.MovieApi;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainMovieFragment extends MovieFragment {

    public static final int MAIN_MOVIE_ID = 1;
    private MovieApi api;

    public MainMovieFragment() {
        //Required empty public constructor
    }

    public int getAdapterId() {
        return MAIN_MOVIE_ID;
    }

    /**
     * Gets popular movies to display
     */
    public void getMovies() {
        SharedPreferences preferences = getPreferences();
        if (!preferences.getBoolean(MainActivity.MAIN_POPULAR_MOVIES_REFRESHED, false)) {
            preferences.edit().putBoolean(MainActivity.MAIN_POPULAR_MOVIES_REFRESHED, true).apply();
            api = new MovieApi(this);
            api.getPopularMovies();
        } else {
            displayStoredMovies();
        }
    }

    @Override
    public void storeMoviesInDatabase(JSONArray movieList) {
        getRepository().StoreAllMovies(movieList);
    }

    /**
     * Displays only top 20 popular movies from internal SQLite database
     */
    public void displayStoredMovies() {
        List<Movie> movieList = getRepository().getPopularMovies();

        ArrayList<Movie> movieArrayList = new ArrayList<>();
        movieArrayList.addAll(movieList); //conversion from List<Movie> since ROOM api does not return an arraylist for an adapter

        Collections.sort(movieArrayList, MovieSorter.popularComparator);

        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieArrayList);
        movieAdapter.setSource(getActivity(), MAIN_MOVIE_ID);
        getMovieListView().setAdapter(movieAdapter);
    }
}
