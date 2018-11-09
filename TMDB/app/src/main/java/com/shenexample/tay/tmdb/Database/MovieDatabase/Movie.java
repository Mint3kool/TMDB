package com.shenexample.tay.tmdb.Database.MovieDatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.shenexample.tay.tmdb.Database.Converters;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = MovieDAO.TABLE_NAME,
        indices = {@Index(value = {"movie_id", "title", "release_date"}, unique = true)})
@TypeConverters({Converters.class})

public class Movie implements Serializable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String movie_id;
    @NonNull
    private String title;
    @NonNull
    private String release_date;
    private String vote_count;
    private String vote_average;
    private String popularity;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    @Ignore
    private Bitmap movieIcon;
    @NonNull
    private Date lastAccessedDate;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(@NonNull String movie_id) {
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

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
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

    public Bitmap getMovieIcon() {
        return movieIcon;
    }

    public void setMovieIcon(Bitmap value) {
        movieIcon = value;
    }

    public void updateDate() {
        lastAccessedDate = Calendar.getInstance().getTime();
    }

    @NonNull
    public Date getLastAccessedDate() {
        return lastAccessedDate;
    }

    public void setLastAccessedDate(@NonNull Date lastAccessedDate) {
        this.lastAccessedDate = lastAccessedDate;
    }
}
