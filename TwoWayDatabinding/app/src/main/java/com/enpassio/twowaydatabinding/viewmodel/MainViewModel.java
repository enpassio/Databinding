package com.enpassio.twowaydatabinding.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.enpassio.twowaydatabinding.ToyRepository;
import com.enpassio.twowaydatabinding.data.ToyEntry;
import com.enpassio.twowaydatabinding.utils.InjectorUtils;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<ToyEntry>> mToyList;
    private ToyRepository mRepo;
    public final ObservableBoolean isLoading = new ObservableBoolean(true);
    public final ObservableBoolean isEmpty = new ObservableBoolean(false);

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepo = InjectorUtils.provideRepository(application);
    }

    public LiveData<List<ToyEntry>> getToyList() {
        if(mToyList == null){
            mToyList = mRepo.getToyList();
        }
        return mToyList;
    }

    public void insertToy(final ToyEntry toy){
        mRepo.insertToy(toy);
    }

    public void deleteToy(final ToyEntry toy){
        mRepo.deleteToy(toy);
    }

    public boolean shouldShowList() {
        return !isEmpty.get() && !isLoading.get();
    }

}
