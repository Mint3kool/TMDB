package com.shenexample.tay.tmdb.Movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.R;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item_row, parent, false);
        }

        TextView movieName = convertView.findViewById(R.id.movie_name);
        TextView moviePopularity = convertView.findViewById(R.id.movie_popularity);
        TextView movieVoteAverage = convertView.findViewById(R.id.movie_vote_average);
        TextView movieReleaseDate = convertView.findViewById(R.id.movie_release_date);

        return convertView;
    }
}
