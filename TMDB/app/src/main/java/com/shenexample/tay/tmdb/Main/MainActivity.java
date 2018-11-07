package com.shenexample.tay.tmdb.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.shenexample.tay.tmdb.R;

/**
 * Startup activity
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private int savedFragmentId = 0;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //Shared preferences name
    public static final String sharedValues = "shared";
    //Status tag for whether popular movies have been updated on the main screen
    public static final String MAIN_POPULAR_MOVIES_REFRESHED = "main popular movie status";
    //Status tag for whether popular Tv shows have been updated on the main screen
    public static final String MAIN_POPULAR_TV_REFRESHED = "main popular tv status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        setContentView(R.layout.activity_main);

        setupNavigationDrawer();
        Log.d("Testing", "onCreate called");

        sharedPreferences = getSharedPreferences(sharedValues, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //resetting shared preferences on startup
        editor.putBoolean(MAIN_POPULAR_MOVIES_REFRESHED, false);
        editor.putBoolean(MAIN_POPULAR_TV_REFRESHED, false);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupMainActivityTabs();
    }

    /**
     * Sets up the scrollable tabs on the initial screen
     */
    private void setupMainActivityTabs() {
        MainPageAdapter myAdapter = new MainPageAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(myAdapter);

        viewPager.setCurrentItem(savedFragmentId);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Sets up the navigation drawer on the left hand side
     */
    private void setupNavigationDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            savedFragmentId = data.getIntExtra("fragmentId", 0);
            Log.d("New Fragment ID", Integer.toString(savedFragmentId));
        } else {
            //No clue?
        }
    }
}