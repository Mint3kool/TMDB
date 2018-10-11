package com.shenexample.tay.tmdb.Database;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.shenexample.tay.tmdb.Movies.MovieFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class MovieRepository {
    private MovieDAO movieDAO;
    private MovieFragment fragment;

    public MovieRepository(Application app, MovieFragment fragment) {
        TMDBRoomDb db = TMDBRoomDb.getDatabase(app);
        movieDAO = db.movieDAO();
        this.fragment = fragment;
    }

    public Movie getMovie(int id) {
        return movieDAO.getMovie(id);
    }

    public List<Movie> getAllMovies() {
        getAllMoviesAsync task = new getAllMoviesAsync(movieDAO);
        try {
            return task.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class getAllMoviesAsync extends AsyncTask<Void, Void, List<Movie>> {
        private MovieDAO asyncMovieDao;

        getAllMoviesAsync(MovieDAO dao) {
            asyncMovieDao = dao;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            return asyncMovieDao.getAllMovies();
        }
    }

    public void deleteMovies() {
        movieDAO.deleteAll();
    }

    public List<Movie> getPopularMovies() {
        getPopularMoviesAsync task = new getPopularMoviesAsync(movieDAO);
        try {
            return task.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class getPopularMoviesAsync extends AsyncTask<Void, Void, List<Movie>> {
        private MovieDAO asyncMovieDao;

        getPopularMoviesAsync(MovieDAO dao) {
            asyncMovieDao = dao;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            return asyncMovieDao.getPopularMovies();
        }
    }

    public void StoreAllMovies(JSONArray array) {
       storeMoviesTask task = new storeMoviesTask();
       Log.d("Storing", "Got to storing all movies");
        try {
            task.execute(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class storeMoviesTask extends AsyncTask<JSONArray, Void, Void> {

        @Override
        protected Void doInBackground(JSONArray... jsonArrays) {
            JSONArray myArray = jsonArrays[0];
            for (int i = 0; i < myArray.length(); i++) {
                try {
                    Movie movie = convertJsonToMovie(myArray.getJSONObject(i));
                    insertAsync(movie);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("MovieAccessError", "Movie at index " + i + " not found, skipping.");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            fragment.displayMovies();
        }
    }

    public void insertAsync(Movie movie) {
        new insertMovieAsync(movieDAO).execute(movie);
    }

    private class insertMovieAsync extends AsyncTask<Movie, Void, Void> {
        private MovieDAO asyncMovieDao;

        insertMovieAsync (MovieDAO dao) {
            asyncMovieDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie ... params) {
            asyncMovieDao.insert(params[0]);
            return null;
        }
    }

    private Movie convertJsonToMovie(JSONObject o) {
        Movie myMovie = new Movie();
        try {
            String posterPath = o.getString("poster_path");
            myMovie.setVote_count(o.getString("vote_count"));
            myMovie.setMovie_id(o.getString("id"));
            myMovie.setVote_average(o.getString("vote_average"));
            myMovie.setTitle(o.getString("title"));
            myMovie.setPopularity(o.getString("popularity"));
            myMovie.setPoster_path(posterPath);
            myMovie.setBackdrop_path(o.getString("backdrop_path"));
            myMovie.setOverview(o.getString("overview"));
            myMovie.setRelease_date(o.getString("release_date"));

            //String path = "http://image.tmdb.org/t/p/w300" + posterPath;
            //downloadImageTask task = new downloadImageTask(myMovie);
            //task.execute();
        } catch (Exception e) {
            Log.d("MovieConversionError", "Could not convert json to movie object. ");
            e.printStackTrace();
        }

        return myMovie;
    }

    private class downloadImageTask extends AsyncTask<String, Void, Bitmap> {
        Movie movie;
        public downloadImageTask(Movie movie) {
            this.movie = movie;
        }

        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap icon = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                icon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return icon;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            movie.setMovieIconBitmap(bitmap);
            insertAsync(movie);
        }
    }
}
