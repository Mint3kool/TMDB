package com.shenexample.tay.tmdb.Database.TVDatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.shenexample.tay.tmdb.Database.Converters;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = ShowDAO.TABLE_NAME,
        indices = {@Index(value = {"show_id", "show_name", "first_air_date"}, unique = true)})
@TypeConverters(Converters.class)
public class Show implements Serializable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String show_id;
    @NonNull
    private String show_name;
    @NonNull
    private String first_air_date;
    private String popularity;
    private String vote_count;
    private String poster_path;
    private String backdrop_path;
    private String vote_average;
    private String overview;
    @Ignore
    private byte[] showIcon;
    @NonNull
    private Date lastAccessedDate;

    public byte[] getShowIcon() {
        return showIcon;
    }

    public void setShowIcon(byte[] showIcon) {
        this.showIcon = showIcon;
    }

    @NonNull
    public Date getLastAccessedDate() {
        return lastAccessedDate;
    }

    public void setLastAccessedDate(@NonNull Date lastAccessedDate) {
        this.lastAccessedDate = lastAccessedDate;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getShow_id() {
        return show_id;
    }

    public void setShow_id(@NonNull String show_id) {
        this.show_id = show_id;
    }

    @NonNull
    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(@NonNull String show_name) {
        this.show_name = show_name;
    }

    @NonNull
    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(@NonNull String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
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

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
