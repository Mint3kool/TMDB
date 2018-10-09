package com.shenexample.tay.tmdb.Movies;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shenexample.tay.tmdb.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tay on 4/26/2018.
 * Class that directly links to the api for getting information on various types of movies
 */
public class MovieApi implements Response.Listener<String>, Response.ErrorListener {

    private RequestQueue queue;
    private MovieFragment myMovieFragment;
    private final String SITEDOMAIN = "http://api.mygasfeed.com";

    public MovieApi(MovieFragment frag) {
        myMovieFragment = frag;
        queue = Volley.newRequestQueue(myMovieFragment.getContext());
    }

    public void getPopularMovies() {
        String requestString = "https://api.themoviedb.org/3/movie/popular?api_key="+ Static.apiKey +"&language=en-US&page=1";
        StringRequest request = new StringRequest(Request.Method.GET, requestString, this, this);

        int retryCount = 5;
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, retryCount, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            myMovieFragment.StoreMoviesInDatabase(jsonArray);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}