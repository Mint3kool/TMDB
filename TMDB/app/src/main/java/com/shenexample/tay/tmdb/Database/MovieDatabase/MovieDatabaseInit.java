package com.shenexample.tay.tmdb.Database.MovieDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabaseInit extends RoomDatabase{

    public abstract MovieDAO movieDAO();

    public static MovieDatabaseInit INSTANCE;

    /**
     * Sets up the local database connection
     * @param ctx the current application context
     * @return a valid database instance
     */
    static MovieDatabaseInit getDatabase(final Context ctx) {
        if (INSTANCE == null) {
            synchronized (MovieDatabaseInit.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                            MovieDatabaseInit.class, MovieDAO.TABLE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
