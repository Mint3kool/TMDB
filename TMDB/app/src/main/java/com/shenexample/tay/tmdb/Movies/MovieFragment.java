package com.shenexample.tay.tmdb.Movies;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieDAO;
import com.shenexample.tay.tmdb.Database.MovieRepository;
import com.shenexample.tay.tmdb.Database.TMDBRoomDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieFragment extends Fragment {


    public MovieFragment() {
        // Required empty public constructor
    }

    public void ConvertJsonToMovies(JSONArray movieArray) {
        new StoreMoviesTask(TMDBRoomDb.INSTANCE).execute(movieArray);
    }

    public void DisplayMovies() {

    }

    private static class StoreMoviesTask extends AsyncTask<JSONArray, Void, Void> {
        private MovieDAO movieDAO;

        public StoreMoviesTask(TMDBRoomDb db) {
            movieDAO = db.movieDAO();
        }

        @Override
        protected Void doInBackground(JSONArray... jsonArrays) {
            JSONArray myArray = jsonArrays[0];
            for (int i = 0; i < myArray.length(); i++) {
                try {
                    Movie newMovie = convertToMovie(myArray.getJSONObject(i));
                    movieDAO.insert(newMovie);
                } catch (Exception e) {
                    Log.d("MovieAccessError", "Movie at index " + i + " not found, skipping.");
                }
            }
            return null;
        }

        private Movie convertToMovie(JSONObject o) {
            Movie myMovie = new Movie();
            try {
                myMovie.setVote_count("vote_count");
                myMovie.setMovie_id("id");
                myMovie.setVote_average("vote_average");
                myMovie.setTitle("title");
                myMovie.setPopularity("popularity");
                myMovie.setPoster_path("poster_path");
                myMovie.setBackdrop_path("backdrop_path");
                myMovie.setOverview("overview");
                myMovie.setRelease_date("release_date");
            } catch (Exception e) {
                Log.d("MovieConversionError", "Could not convert json to movie object. ");
                e.printStackTrace();
            }
            return myMovie;
        }
    }
}
