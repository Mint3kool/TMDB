package com.shenexample.tay.tmdb.Movies;

import android.os.AsyncTask;
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
import com.shenexample.tay.tmdb.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PopularMovieFragment extends MovieFragment{

    private MovieApi api;
    private ListView movieListView;


    public PopularMovieFragment() {
        //Required empty public constructor
    }

    @Override
    public void displayMovies() {
        List<Movie> movieArray = getRepository().getAllMovies();
        ArrayList<Movie> movieArrayList = new ArrayList<>(movieArray);
        Log.d("size", Integer.toString(movieArrayList.size()));
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieArrayList);

        movieListView.setAdapter(movieAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        movieListView = rootView.findViewById(R.id.movie_list);
        return rootView;
    }

    public void getMovies() {
        api = new MovieApi(this);
        api.getPopularMovies();
    }

    @Override
    public void storeMoviesInDatabase(JSONArray movieArray) {

    }
}
