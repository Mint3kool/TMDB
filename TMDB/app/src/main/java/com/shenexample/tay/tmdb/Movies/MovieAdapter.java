package com.shenexample.tay.tmdb.Movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

    Activity sourceActivity;
    int sourceAdapterId;
    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    public void setSource(Activity activity, int id) {
        sourceActivity = activity;
        sourceAdapterId = id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item_row, parent, false);
        }

        TextView movieTitle = convertView.findViewById(R.id.movie_title);
        movieTitle.setText(movie.getTitle());

        TextView movieVoteAverage = convertView.findViewById(R.id.movie_vote_average);
        movieVoteAverage.setText(movie.getVote_average());

        TextView movieReleaseDate = convertView.findViewById(R.id.movie_release_date);
        movieReleaseDate.setText(movie.getRelease_date());

        ImageView movieIcon = convertView.findViewById(R.id.movie_icon);
        try {
            movieIcon.setImageBitmap(movie.getMovieIconBitmap());
        } catch (NullPointerException e) {
            Log.d("Missing Image", movie.getTitle());
            movieIcon.setImageResource(R.drawable.ic_launcher_background);
        }


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra("fragmentId", sourceAdapterId);
                intent.putExtra("myMovie", movie);
                sourceActivity.startActivityForResult(intent, 0);
            }
        });

        return convertView;
    }
}
