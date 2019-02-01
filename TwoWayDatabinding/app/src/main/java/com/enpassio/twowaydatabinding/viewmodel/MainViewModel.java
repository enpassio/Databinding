package com.enpassio.twowaydatabinding.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.enpassio.twowaydatabinding.ToyRepository;
import com.enpassio.twowaydatabinding.data.ToyEntry;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<ToyEntry>> mToyList;
    private ToyRepository mRepo;
    public final ObservableBoolean isLoading = new ObservableBoolean(true);
    public final ObservableBoolean isEmpty = new ObservableBoolean(false);
    private MutableLiveData<ToyEntry> mChosenToy;

    public MainViewModel(ToyRepository repository) {
        mRepo = repository;
        loadToyList();
    }

    public void loadToyList(){
        if(mToyList == null){
            mToyList = mRepo.getToyList();
        }
    }

    public LiveData<List<ToyEntry>> getToyList() {
        return mToyList;
    }

    public void insertToy(final ToyEntry toy){
        mRepo.insertToy(toy);
    }

    public void updateToy(final ToyEntry toy){
        mRepo.updateToy(toy);
    }

    public void deleteToy(final ToyEntry toy){
        mRepo.deleteToy(toy);
    }

    public boolean shouldShowList() {
        return !isEmpty.get() && !isLoading.get();
    }

    public LiveData<ToyEntry> getChosenToy() {
        return mChosenToy;
    }

    public void setChosenToy(ToyEntry toy) {
        mChosenToy.setValue(toy);
    }
}
