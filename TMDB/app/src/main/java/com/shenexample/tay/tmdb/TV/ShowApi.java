package com.shenexample.tay.tmdb.TV;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shenexample.tay.tmdb.Static;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowApi implements Response.Listener<String>, Response.ErrorListener{

    private RequestQueue queue;
    private ShowFragment myShowFragment;

    public ShowApi(ShowFragment frag) {
        myShowFragment = frag;
        queue = Volley.newRequestQueue(myShowFragment.getContext());
    }

    public void getPopularShows() {
        String requestString = "https://api.themoviedb.org/3/tv/popular?api_key=" + Static.apiKey + "&language=en-US&page=1";
        StringRequest request = new StringRequest(Request.Method.GET, requestString, this, this);

        int retryCount = 5;
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, retryCount, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray array = jsonObject.getJSONArray("results");
            myShowFragment.storeShowsInDatabase(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
