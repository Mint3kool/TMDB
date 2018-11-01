package com.shenexample.tay.tmdb.Movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.R;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item_row, parent, false);
        }

        TextView movieTitle = convertView.findViewById(R.id.movie_title);
        movieTitle.setText(movie.getTitle());

        TextView movieVoteAverage = convertView.findViewById(R.id.movie_vote_average);
        //movieVoteAverage.setText(movie.getVote_average());
        movieVoteAverage.setText(movie.getPopularity());

        TextView movieReleaseDate = convertView.findViewById(R.id.movie_release_date);
        movieReleaseDate.setText(movie.getRelease_date());

        ImageView movieIcon = convertView.findViewById(R.id.movie_icon);
        movieIcon.setImageBitmap(movie.getMovieIconBitmap());

        return convertView;
    }
}
