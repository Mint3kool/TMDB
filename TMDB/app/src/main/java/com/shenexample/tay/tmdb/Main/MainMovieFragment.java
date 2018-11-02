package com.shenexample.tay.tmdb.Main;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieSorter;
import com.shenexample.tay.tmdb.Movies.MovieAdapter;
import com.shenexample.tay.tmdb.Movies.MovieFragment;
import com.shenexample.tay.tmdb.Movies.MovieApi;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;

public class MainMovieFragment extends MovieFragment {

    public static final int MAIN_MOVIE_ID = 1;
    private MovieApi api;

    public MainMovieFragment() {
        //Required empty public constructor
    }

    public int getAdapterId() {
        return MAIN_MOVIE_ID;
    }

    public void getMovies() {
        api = new MovieApi(this);
        api.getPopularMovies();
    }

    @Override
    public void storeMoviesInDatabase(ArrayList<Movie> selectedMovies) {
        //getRepository().StoreAllMovies(movieArray);
    }

    public void processMovies(JSONArray movieArray) {
        convertJsonArrayToArrayList(movieArray);
    }

    public void displayMovies(ArrayList<Movie> movieArrayList) {
        Collections.sort(movieArrayList, MovieSorter.popularComparator);

        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieArrayList);
        movieAdapter.setSource(getActivity(), MAIN_MOVIE_ID);
        getMovieListView().setAdapter(movieAdapter);
    }
}
