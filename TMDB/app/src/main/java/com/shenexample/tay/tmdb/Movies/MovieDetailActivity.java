package com.shenexample.tay.tmdb.Movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shenexample.tay.tmdb.R;

public class MovieDetailActivity extends AppCompatActivity {

    int sourceFragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        sourceFragmentId = getIntent().getIntExtra("fragmentId", 0);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("fragmentId", sourceFragmentId);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
