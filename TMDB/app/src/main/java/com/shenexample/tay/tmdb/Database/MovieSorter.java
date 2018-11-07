package com.shenexample.tay.tmdb.Database;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Custom sorters by various movie fields
 */
public class MovieSorter {

    public static Comparator<Movie> popularComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            double pop1 = Double.parseDouble(movie.getPopularity());
            double pop2 = Double.parseDouble(t1.getPopularity());

            if (pop1 <= pop2) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    public static Comparator<Movie> dateComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie, Movie t1) {
            Date date1 = null, date2 = null;
            try {
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(movie.getRelease_date());
                date2 = new SimpleDateFormat("yyyy-MM-dd").parse(t1.getRelease_date());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (date1.before(date2) && !date1.after(date2)) {
                return -1;
            } else {
                return 1;
            }
        }
    };
}
