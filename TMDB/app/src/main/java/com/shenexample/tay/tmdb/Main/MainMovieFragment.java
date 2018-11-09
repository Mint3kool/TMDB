package com.shenexample.tay.tmdb.Main;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.SparseArray;

import com.shenexample.tay.tmdb.Database.MovieDatabase.Movie;
import com.shenexample.tay.tmdb.Database.MovieDatabase.MovieSorter;
import com.shenexample.tay.tmdb.Movies.MovieAdapter;
import com.shenexample.tay.tmdb.Movies.MovieFragment;
import com.shenexample.tay.tmdb.Movies.MovieApi;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainMovieFragment extends MovieFragment {

    public static final int MAIN_MOVIE_ID = 1;
    private SparseArray<Bitmap> popularMovieImages = null;
    private MovieApi api;

    public MainMovieFragment() {
        //Required empty public constructor
    }

    public int getAdapterId() {
        return MAIN_MOVIE_ID;
    }

    /**
     * Gets popular movies to display
     */
    public void getMovies() {
        SharedPreferences preferences = getMoviePreferences();
        if (!preferences.getBoolean(MainActivity.MAIN_POPULAR_MOVIES_REFRESHED, false)) {
            preferences.edit().putBoolean(MainActivity.MAIN_POPULAR_MOVIES_REFRESHED, true).apply();
            api = new MovieApi(this);
            api.getPopularMovies();
        } else {
            displayStoredMovies();
        }
    }

    @Override
    public void storeMoviesInDatabase(JSONArray movieList) {
        getRepository().StoreAllMovies(movieList);
    }

    /**
     * Displays only top 20 popular movies from internal SQLite database
     */
    public void displayStoredMovies() {
        List<Movie> movieList = getRepository().getPopularMovies();

        ArrayList<Movie> movieArrayList = new ArrayList<>();
        movieArrayList.addAll(movieList); //conversion from List<Movie> since ROOM api does not return an arraylist for an adapter

        new setMovieImagesTask().execute(movieArrayList);
    }

    public void addToFragment(ArrayList<Movie> movieArrayList) {
        Collections.sort(movieArrayList, MovieSorter.popularComparator);

        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieArrayList);
        movieAdapter.setSource(getActivity(), MAIN_MOVIE_ID);
        getMovieListView().setAdapter(movieAdapter);
    }

    private class setMovieImagesTask extends AsyncTask<ArrayList<Movie>, ArrayList<Movie>, ArrayList<Movie>> {
        ArrayList<Movie> movieArrayList;

        @Override
        protected ArrayList<Movie> doInBackground(ArrayList<Movie>... arrayLists) {
            movieArrayList = arrayLists[0];

            if (popularMovieImages == null) {
                popularMovieImages = new SparseArray<>(20);
                for (Movie m : movieArrayList) {
                    try {
                        String path = "http://image.tmdb.org/t/p/w300" + m.getPoster_path();

                        InputStream in = new java.net.URL(path).openStream();
                        Bitmap icon = BitmapFactory.decodeStream(in);
                        popularMovieImages.put(m.getId(), icon);
                        m.setMovieIcon(icon);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                for (Movie m : movieArrayList) {
                    m.setMovieIcon(popularMovieImages.get(m.getId()));
                }
            }
            return movieArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movieArrayList) {
            addToFragment(movieArrayList);
        }
    }
}
