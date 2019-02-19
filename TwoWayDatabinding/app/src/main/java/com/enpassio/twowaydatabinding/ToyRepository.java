package com.enpassio.twowaydatabinding;

import android.arch.lifecycle.LiveData;

import com.enpassio.twowaydatabinding.data.ToyDatabase;
import com.enpassio.twowaydatabinding.data.ToyEntry;
import com.enpassio.twowaydatabinding.utils.AppExecutors;

import java.util.List;

public class ToyRepository {

    private static ToyRepository sInstance;
    private final ToyDatabase mDatabase;
    private final LiveData<List<ToyEntry>> toyList;
    private final AppExecutors mExecutors;

    private ToyRepository(ToyDatabase database, AppExecutors executors) {
        mDatabase = database;
        mExecutors = executors;
        toyList = loadToys();
    }

    public static ToyRepository getInstance(ToyDatabase database, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (ToyRepository.class) {
                sInstance = new ToyRepository(database, executors);
            }
        }
        return sInstance;
    }

    private LiveData<List<ToyEntry>> loadToys(){
        return mDatabase.toyDao().getAllToys();
    }

    public LiveData<List<ToyEntry>> getToyList() {
        return toyList;
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
