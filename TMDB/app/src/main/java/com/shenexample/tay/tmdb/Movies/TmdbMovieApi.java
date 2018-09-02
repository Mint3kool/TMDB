package com.shenexample.tay.tmdb.Movies;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tay on 4/26/2018.
 * Class that directly links to the api for our gas site
 */
public class TmdbMovieApi implements Response.Listener<String>, Response.ErrorListener {

    private RequestQueue queue;
    private final String SITEDOMAIN = "http://api.mygasfeed.com";

    public TmdbMovieApi(Context ctx) {
        queue = Volley.newRequestQueue(ctx);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("stations");
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}