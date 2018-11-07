package com.shenexample.tay.tmdb.Database.TVDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shenexample.tay.tmdb.Database.MovieDatabase.MovieDatabaseInit;

@Database(entities = {Show.class}, version = 1)
public abstract class ShowDatabaseInit extends RoomDatabase{

    public abstract ShowDAO ShowDAO();

    public static ShowDatabaseInit INSTANCE;

    static ShowDatabaseInit getDatabase(final Context ctx) {
        if (INSTANCE == null) {
            synchronized (MovieDatabaseInit.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                            ShowDatabaseInit.class, ShowDAO.TABLE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
