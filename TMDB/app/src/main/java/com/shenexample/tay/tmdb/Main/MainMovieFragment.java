package com.shenexample.tay.tmdb.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieRepository;
import com.shenexample.tay.tmdb.Movies.MovieAdapter;
import com.shenexample.tay.tmdb.Movies.MovieFragment;
import com.shenexample.tay.tmdb.Movies.MovieApi;
import com.shenexample.tay.tmdb.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainMovieFragment extends MovieFragment {

    private MovieApi api;

    public MainMovieFragment() {
        //Required empty public constructor
    }

    public void getMovies() {
        api = new MovieApi(this);
        api.getPopularMovies();
    }

    @Override
    public void storeMoviesInDatabase(JSONArray movieArray) {
        getRepository().StoreAllMovies(movieArray);
    }

    public void displayMovies() {
        List<Movie> movieArray = getRepository().getPopularMovies();
        ArrayList<Movie> movieArrayList = new ArrayList<>(movieArray);
        Log.d("size", Integer.toString(movieArrayList.size()));
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieArrayList);

        getMovieListView().setAdapter(movieAdapter);
    }
}
