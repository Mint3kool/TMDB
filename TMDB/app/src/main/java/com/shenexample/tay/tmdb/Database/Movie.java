package com.shenexample.tay.tmdb.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = MovieDAO.TABLE_NAME)
public class Movie {

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(@NonNull int movie_id) {
        this.movie_id = movie_id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(@NonNull String release_date) {
        this.release_date = release_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private int movie_id;
    @NonNull
    private String title;
    @NonNull
    private String release_date;
    private int vote_count;
    private double popularity;
    private String poster_path;
    private String backdrop_path;
    private String overview;

}
