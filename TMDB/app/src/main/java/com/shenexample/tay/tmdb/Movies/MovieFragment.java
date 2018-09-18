package com.shenexample.tay.tmdb.Movies;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieRepository;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class MovieFragment extends Fragment {

    public MovieFragment() {
        // Required empty public constructor
    }

    public abstract void DisplayMovies();

    public abstract void GetMovies();

    public abstract void RefreshDatabase();

    public abstract MovieRepository getRepository();

    public void StoreMoviesInDatabase(JSONArray movieArray) {
        new StoreMoviesTask(getRepository()).execute(movieArray);
    }

    public void ClearDatabase() {
        new ClearDatabaseTask(getRepository()).execute();
    }

    public Movie convertToMovie(JSONObject o) {
        Movie myMovie = new Movie();
        try {
            myMovie.setVote_count(o.getString("vote_count"));
            myMovie.setMovie_id(o.getString("id"));
            myMovie.setVote_average(o.getString("vote_average"));
            myMovie.setTitle(o.getString("title"));
            myMovie.setPopularity(o.getString("popularity"));
            myMovie.setPoster_path(o.getString("poster_path"));
            myMovie.setBackdrop_path(o.getString("backdrop_path"));
            myMovie.setOverview(o.getString("overview"));
            myMovie.setRelease_date(o.getString("release_date"));
        } catch (Exception e) {
            Log.d("MovieConversionError", "Could not convert json to movie object. ");
            e.printStackTrace();
        }
        return myMovie;
    }

    private class ClearDatabaseTask extends AsyncTask<Void, Void, Void> {
        private MovieRepository movieRepository;

        public ClearDatabaseTask(MovieRepository repo) {
            movieRepository = repo;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieRepository.deleteMovies();
            return null;
        }
    }

    private class StoreMoviesTask extends AsyncTask<JSONArray, Void, Void> {
        private MovieRepository movieRepository;

        public StoreMoviesTask(MovieRepository repo) {
            movieRepository = repo;
        }

        @Override
        protected Void doInBackground(JSONArray... jsonArrays) {
            JSONArray myArray = jsonArrays[0];
            for (int i = 0; i < myArray.length(); i++) {
                try {
                    Movie newMovie = convertToMovie(myArray.getJSONObject(i));
                    movieRepository.insert(newMovie);
                } catch (Exception e) {
                    Log.d("MovieAccessError", "Movie at index " + i + " not found, skipping.");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DisplayMovies();
        }
    }


}
