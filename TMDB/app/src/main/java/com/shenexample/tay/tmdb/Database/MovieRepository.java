package com.shenexample.tay.tmdb.Database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class MovieRepository {
    private MovieDAO movieDAO;

    MovieRepository(Application app) {
        TMDBRoomDb db = TMDBRoomDb.getDatabase(app);
        movieDAO = db.movieDAO();
    }

    public Movie getMovie(int id) {
        return movieDAO.getMovie(id);
    }

    public List<Movie> getMovies(String field) {
        return movieDAO.findMoviesOrderByField(field);
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
}
