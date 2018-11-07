package com.shenexample.tay.tmdb.TV;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shenexample.tay.tmdb.Database.TVDatabase.ShowRepository;
import com.shenexample.tay.tmdb.Main.MainActivity;
import com.shenexample.tay.tmdb.R;

public abstract class ShowFragment extends Fragment implements ShowInterface {

    private ShowRepository showRepository;
    private ListView showListView;
    private SharedPreferences showPreferences;

    public ShowFragment() {
        // Required empty public constructor
    }

    public ShowRepository getRepository() {
        return showRepository;
    }

    public ListView getShowListView() {
        return showListView;
    }

    public SharedPreferences getShowPreferences() {
        return showPreferences;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tv, container, false);
        showListView = rootView.findViewById(R.id.movie_list);
        showPreferences = getActivity().getApplicationContext().getSharedPreferences(MainActivity.sharedValues, Context.MODE_PRIVATE);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showRepository = new ShowRepository(getActivity().getApplication(), this);
        getShows();
    }
}
