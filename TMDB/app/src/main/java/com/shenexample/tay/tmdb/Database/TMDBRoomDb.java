package com.shenexample.tay.tmdb.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Movie.class}, version = 1)
public abstract class TMDBRoomDb extends RoomDatabase{

    public abstract MovieDAO movieDAO();

    public static TMDBRoomDb INSTANCE;

    static TMDBRoomDb getDatabase(final Context ctx) {
        if (INSTANCE == null) {
            synchronized (TMDBRoomDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                            TMDBRoomDb.class, MovieDAO.TABLE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
