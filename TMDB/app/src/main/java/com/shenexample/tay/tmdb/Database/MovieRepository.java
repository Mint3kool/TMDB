package com.shenexample.tay.tmdb.Database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieRepository {
    private MovieDAO movieDAO;

    public MovieRepository(Application app) {
        TMDBRoomDb db = TMDBRoomDb.getDatabase(app);
        movieDAO = db.movieDAO();
    }

    public Movie getMovie(int id) {
        return movieDAO.getMovie(id);
    }

    public void deleteMovies() {
        movieDAO.deleteAll();
    }

    public ArrayList<Movie> getAllMovies() {
        getAllMoviesAsync task = new getAllMoviesAsync(movieDAO);
        try {
            return task.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insert(Movie movie) {
        new insertMovieAsync(movieDAO).execute(movie);
    }

    private static class insertMovieAsync extends AsyncTask<Movie, Void, Void> {
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

    private class getAllMoviesAsync extends AsyncTask<Void, Void, ArrayList<Movie>> {
        private MovieDAO asyncMovieDao;

        getAllMoviesAsync(MovieDAO dao) {
            asyncMovieDao = dao;
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            return asyncMovieDao.getAllMovies();
        }
    }
}
