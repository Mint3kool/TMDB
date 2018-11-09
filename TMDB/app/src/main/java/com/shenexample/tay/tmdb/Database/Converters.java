package com.shenexample.tay.tmdb.Database;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Any necessary conversions for Java types to SQLite information
 */
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
