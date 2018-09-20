package com.shenexample.tay.tmdb.Movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieRepository;
import com.shenexample.tay.tmdb.R;

import java.util.ArrayList;
import java.util.List;

public class PopularMovieFragment extends MovieFragment{

    private TmdbMovieApi api;
    private MovieRepository myRepository;


    public PopularMovieFragment() {
        //Required empty public constructor
    }

    @Override
    public void DisplayMovies() {
        ArrayList<Movie> movieArray = myRepository.getAllMovies();
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieArray);
    }

    @Override
    public MovieRepository getRepository() {
        return myRepository;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRepository = new MovieRepository(getActivity().getApplication());
        RefreshDatabase();
    }

    @Override
    public void GetMovies() {
        api = new TmdbMovieApi(this);
        api.getPopularMovies();
    }

    @Override
    public void RefreshDatabase() {
        new RefreshDatabaseTask(getRepository()).execute();
    }

    private class RefreshDatabaseTask extends AsyncTask<Void, Void, Void> {
        private MovieRepository movieRepository;

        public RefreshDatabaseTask(MovieRepository repo) {
            movieRepository = repo;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieRepository.deleteMovies();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            GetMovies();
        }
    }
}
