package com.shenexample.tay.tmdb.Movies;

import android.app.Application;
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

import java.util.List;

public class MovieFragment extends Fragment {

    private MovieRepository myRepo;

    public MovieFragment() {
        // Required empty public constructor
    }

    public void StoreMovies(JSONArray movieArray) {
        myRepo = new MovieRepository(getActivity().getApplication());
        new StoreMoviesTask(myRepo).execute(movieArray);
    }

    public void DisplayMovies() {
        List<Movie> myList = myRepo.getAllMovies();
        int size = myList.size();
    }

    private class StoreMoviesTask extends AsyncTask<JSONArray, Void, Void> {
        private MovieRepository movieRepo;

        public StoreMoviesTask(MovieRepository repo) {
            movieRepo = repo;
        }

        @Override
        protected Void doInBackground(JSONArray... jsonArrays) {
            JSONArray myArray = jsonArrays[0];
            for (int i = 0; i < myArray.length(); i++) {
                try {
                    Movie newMovie = convertToMovie(myArray.getJSONObject(i));
                    movieRepo.insert(newMovie);
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

        @Override
        protected void onPostExecute(Void aVoid) {
            DisplayMovies();
        }
    }


}
