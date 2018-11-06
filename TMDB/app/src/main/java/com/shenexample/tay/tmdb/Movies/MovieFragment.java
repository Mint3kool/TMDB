package com.shenexample.tay.tmdb.Movies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shenexample.tay.tmdb.Database.Movie;
import com.shenexample.tay.tmdb.Database.MovieRepository;
import com.shenexample.tay.tmdb.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public abstract class MovieFragment extends Fragment implements MovieInterface{

    private MovieRepository myRepository;
    private ListView movieListView;

    public MovieFragment() {
        // Required empty public constructor
    }

    public MovieRepository getRepository() {
        return myRepository;
    }

    public ListView getMovieListView() { return movieListView; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        movieListView = rootView.findViewById(R.id.movie_list);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRepository = new MovieRepository(getActivity().getApplication(), this);
        getMovies();
    }

//    public void convertJsonArrayToArrayList(JSONArray array) {
//        new ConvertMovieTask().execute(array);
//    }

//    private class ConvertMovieTask extends AsyncTask<JSONArray, Void, ArrayList<Movie>> {
//        ArrayList<Movie> myMovieList = new ArrayList<>();
//
//        protected ArrayList<Movie> doInBackground(JSONArray... arrays) {
//            JSONArray movieArray = arrays[0];
//
//            for (int i = 0; i < movieArray.length(); i++) {
//                try {
//                    Movie movie = convertJsonToMovie(movieArray.getJSONObject(i));
//                    String path = "http://image.tmdb.org/t/p/w300" + movie.getPoster_path();
//
//                    try {
//                        InputStream in = new java.net.URL(path).openStream();
//                        Bitmap icon = BitmapFactory.decodeStream(in);
//                        movie.setMovieIconBitmap(icon);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    myMovieList.add(movie);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.d("MovieAccessError", "Movie at index " + i + " not found, skipping.");
//                }
//            }
//
//            return myMovieList;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Movie> movieArrayList) {
//            displayStoredMovies(movieArrayList);
//        }
//    }
}


