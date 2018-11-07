package com.shenexample.tay.tmdb.Database.TVDatabase;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

public interface ShowDAO {
    String TABLE_NAME = "Show_Table";

    //Finds the last accessed date of the 500th item and deletes all items older than it
    String REMOVE_EXTRA_ROWS = "" +
            "delete from "+ TABLE_NAME +" where lastAccessedDate < (" +
            "select lastAccessedDate from (" +
            "select * from " + TABLE_NAME + " order by lastAccessedDate desc Limit 500" +
            ") order by lastAccessedDate asc limit 1)";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Show show);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + TABLE_NAME )
    List<Show> getAllShows();

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY popularity LIMIT 20")
    List<Show> getPopularShows();

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY :category LIMIT :start, :finish")
    List<Show> getNextPage(String category, int start, int finish);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE show_id = :id")
    Show getShow(int id);

    /**
     * Removes the 500 oldest items
     */
    @Query(REMOVE_EXTRA_ROWS)
    void deleteExtraShows();

    /**
     * Deletes all items last accessed before a certain date
     * @param date the oldest item you want to save
     */
    @Query("DELETE FROM " + TABLE_NAME + " WHERE lastAccessedDate < :date")
    void deleteOldShows(Long date);
}
