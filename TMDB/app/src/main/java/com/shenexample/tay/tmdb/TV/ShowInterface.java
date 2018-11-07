package com.shenexample.tay.tmdb.TV;

import org.json.JSONArray;

public interface ShowInterface {
    void getShows();
    void displayStoredShows();
    void storeShowsInDatabase(JSONArray movieList);
}
