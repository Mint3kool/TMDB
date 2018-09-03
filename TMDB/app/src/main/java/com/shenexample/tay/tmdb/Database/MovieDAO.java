package com.shenexample.tay.tmdb.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {
    String TABLE_NAME = "Movie_Table";

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + TABLE_NAME )
    List<Movie> getAllMovies();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE movie_id = :id")
    Movie getMovie(int id);
}
