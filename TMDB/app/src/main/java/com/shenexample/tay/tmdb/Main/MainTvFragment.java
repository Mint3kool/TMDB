package com.shenexample.tay.tmdb.Main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenexample.tay.tmdb.R;

public class MainTvFragment extends Fragment {

    public static final int MAIN_TV_ID = 2;

    public MainTvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tv, container, false);
        return rootView;
    }
}
