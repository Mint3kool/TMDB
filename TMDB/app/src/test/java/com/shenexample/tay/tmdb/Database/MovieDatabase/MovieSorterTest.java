package com.shenexample.tay.tmdb.Database.MovieDatabase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

public class MovieSorterTest {

    private ArrayList<Movie> movieArrayList;

    @Before
    public void setUp() throws Exception {
        movieArrayList = new ArrayList<>();
    }

    @Test
    public void testPopularSorter() {
        Movie movie1 = new Movie();
        movie1.setPopularity("2.25");
        movieArrayList.add(movie1);

        Movie movie2 = new Movie();
        movie2.setPopularity("10.5");
        movieArrayList.add(movie2);

        Movie movie3 = new Movie();
        movie3.setPopularity("8.5");
        movieArrayList.add(movie3);

        Collections.sort(movieArrayList, MovieSorter.popularComparator);
        assertEquals(movie2, movieArrayList.get(0));
        assertEquals(movie3, movieArrayList.get(1));
        assertEquals(movie1, movieArrayList.get(2));
    }

    @Test
    public void testDateSorter() {
        Movie movie1 = new Movie();
        movie1.setRelease_date("2018-10-03");
        movieArrayList.add(movie1);

        Movie movie2 = new Movie();
        movie2.setRelease_date("2015-10-03");
        movieArrayList.add(movie2);

        Movie movie3 = new Movie();
        movie3.setRelease_date("2017-10-03");
        movieArrayList.add(movie3);

        Collections.sort(movieArrayList, MovieSorter.releaseDateComparator);
        assertEquals(movie1, movieArrayList.get(0));
        assertEquals(movie3, movieArrayList.get(1));
        assertEquals(movie2, movieArrayList.get(2));
    }
}