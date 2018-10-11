package com.shenexample.tay.tmdb.Movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shenexample.tay.tmdb.Database.MovieRepository;
import com.shenexample.tay.tmdb.R;

public abstract class MovieFragment extends Fragment implements MovieInterface{

    private MovieRepository myRepository;
    private ListView movieListView;

    public MovieFragment() {
        // Required empty public constructor
    }

    public MovieRepository getRepository() {
        return myRepository;
    }

    public ListView getMovieListView() { return movieListView; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        movieListView = rootView.findViewById(R.id.movie_list);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRepository = new MovieRepository(getActivity().getApplication(), this);
        getMovies();
    }
}


