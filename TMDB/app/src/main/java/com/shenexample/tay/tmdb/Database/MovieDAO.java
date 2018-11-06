package com.shenexample.tay.tmdb.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dao
public interface MovieDAO {
    String TABLE_NAME = "Movie_Table";

    //Finds the last accessed date of the 500th item and deletes all items older than it
    String REMOVE_EXTRA_ROWS = "" +
            "delete from "+ TABLE_NAME +" where lastAccessedDate < (" +
            "select lastAccessedDate from (" +
            "select * from " + TABLE_NAME + " order by lastAccessedDate desc Limit 500" +
            ") order by lastAccessedDate asc limit 1)";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + TABLE_NAME )
    List<Movie> getAllMovies();

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY popularity LIMIT 20")
    List<Movie> getPopularMovies();

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY :category LIMIT :start, :finish")
    List<Movie> getNextPage(String category, int start, int finish);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE movie_id = :id")
    Movie getMovie(int id);

    @Query(REMOVE_EXTRA_ROWS)
    void deleteExtraRows();

    @Query("DELETE FROM " + TABLE_NAME + " WHERE lastAccessedDate < :date")
    void deleteOldItems(Long date);
}
