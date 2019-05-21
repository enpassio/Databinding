package com.enpassio.twowaydatabinding.data;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.enpassio.twowaydatabinding.data.converters.GenderTypeConverter;
import com.enpassio.twowaydatabinding.data.converters.MapConverter;
import com.enpassio.twowaydatabinding.data.converters.ProcurementTypeConverter;
import com.enpassio.twowaydatabinding.data.model.ToyEntry;

@Database(entities = {ToyEntry.class}, version =1, exportSchema = false)
@TypeConverters({MapConverter.class, ProcurementTypeConverter.class, GenderTypeConverter.class})
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
