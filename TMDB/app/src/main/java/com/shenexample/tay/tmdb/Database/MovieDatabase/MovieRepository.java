package com.shenexample.tay.tmdb.Database.MovieDatabase;

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

/**
 * Buffer class for database access logic
 */
public class MovieRepository {
    private MovieDAO movieDAO;
    private MovieFragment fragment;

    public MovieRepository(Application app, MovieFragment fragment) {
        MovieDatabaseInit db = MovieDatabaseInit.getDatabase(app);
        movieDAO = db.movieDAO();
        this.fragment = fragment;
    }

    /**
     * Returns a stored movie by id value
     * @param id the TMDB movie id
     * @return a movie with the associated id, null if not found
     */
    public Movie getMovie(int id) {
        return movieDAO.getMovie(id);
    }

    /**
     * @return a list of all moves currently stored
     */
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

    /**
     * Deletes all items from the SQLite database
     */
    public void deleteMovies() {
        movieDAO.deleteAll();
    }

    /**
     * @return a list of the top 20 popular movies
     */
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

    /**
     * Stores all movies after an api call
     * @param array a JSON response with movies
     */
    public void StoreAllMovies(JSONArray array) {
        storeMoviesTask task = new storeMoviesTask();
        try {
            task.execute(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class storeMoviesTask extends AsyncTask<JSONArray, Integer, Void> {

        @Override
        protected Void doInBackground(JSONArray... jsonArrays) {
            JSONArray myArray = jsonArrays[0];
            for (int i = 0; i < myArray.length(); i++) {
                try {
                    publishProgress((int)((i/(float)myArray.length())*100));
                    Movie movie = convertMovie(myArray.getJSONObject(i));
                    movieDAO.insert(movie);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("MovieAccessError", "Movie at index " + i + " not found, skipping.");
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            Log.d("Progress", Integer.toString(progress[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("Display", "Display movies called");
            fragment.displayStoredMovies();
        }
    }

    /**
     * Converts a Json object to a Movie object
     */
    public Movie convertMovie(JSONObject object){
        Movie myMovie = null;

        try {
            myMovie = convertJsonToMovie(object);
            String path = "http://image.tmdb.org/t/p/w300" + myMovie.getPoster_path();

            try {
                InputStream in = new java.net.URL(path).openStream();
                Bitmap icon = BitmapFactory.decodeStream(in);
                myMovie.setMovieIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("MovieConversionError", "Could not convert " + object.toString());
        }

        return myMovie;
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
            myMovie.updateDate();
        } catch (Exception e) {
            Log.d("MovieConversionError", "Could not convert json to movie object. ");
            e.printStackTrace();
        }

        return myMovie;
    }

    /**
     * @param movie the movie to be inserted
     */
    public void insertMovie(Movie movie) {
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
}
