package com.enpassio.twowaydatabinding.data;

import android.arch.lifecycle.LiveData;

import com.enpassio.twowaydatabinding.data.model.ToyEntry;
import com.enpassio.twowaydatabinding.utils.AppExecutors;

import java.util.List;

public class ToyRepository {

    private static ToyRepository sInstance;
    private final ToyDatabase mDatabase;
    private final AppExecutors mExecutors;

    private ToyRepository(ToyDatabase database, AppExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
    }

    public static ToyRepository getInstance(ToyDatabase database, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (ToyRepository.class) {
                if (sInstance == null) {
                    sInstance = new ToyRepository(database, executors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<ToyEntry>> getToyList() {
        return mDatabase.toyDao().getAllToys();
    }

    public LiveData<ToyEntry> getChosenToy(int toyId){
        return mDatabase.toyDao().getChosenToy(toyId);
    }

    public void insertToy(final ToyEntry toy){
        mExecutors.diskIO().execute(() -> mDatabase.toyDao().insertToy(toy));
    }

    public void updateToy(final ToyEntry toy){
        mExecutors.diskIO().execute(() -> mDatabase.toyDao().updateToyInfo(toy));
    }

    public void deleteToy(final ToyEntry toy){
        mExecutors.diskIO().execute(() -> mDatabase.toyDao().deleteToy(toy));
    }

}
