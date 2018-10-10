package com.shenexample.tay.tmdb.Movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.shenexample.tay.tmdb.Database.MovieRepository;

public abstract class MovieFragment extends Fragment implements MovieInterface{

    private MovieRepository myRepository;

    public MovieFragment() {
        // Required empty public constructor
    }

    public MovieRepository getRepository() {
        return myRepository;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRepository = new MovieRepository(getActivity().getApplication());
    }
}


