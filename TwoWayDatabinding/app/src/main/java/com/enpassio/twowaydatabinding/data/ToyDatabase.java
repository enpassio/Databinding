package com.enpassio.twowaydatabinding.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {ToyEntry.class}, version =1, exportSchema = false)
@TypeConverters(MapConverter.class)
public abstract class ToyDatabase extends RoomDatabase {

    private static final String LOG_TAG = ToyDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "inventory";
    private static ToyDatabase sInstance;

    public static ToyDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ToyDatabase.class, ToyDatabase.DATABASE_NAME)
                            .build();
                    Log.d(LOG_TAG, "Creating new database instance");
                }
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract ToyDao toyDao();
}
