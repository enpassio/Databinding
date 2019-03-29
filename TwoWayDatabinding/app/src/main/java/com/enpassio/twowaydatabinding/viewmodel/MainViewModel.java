package com.enpassio.twowaydatabinding.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.enpassio.twowaydatabinding.ToyRepository;
import com.enpassio.twowaydatabinding.data.ToyEntry;
import com.enpassio.twowaydatabinding.utils.InjectorUtils;
import com.enpassio.twowaydatabinding.utils.UIState;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<ToyEntry>> mToyList;
    private ToyRepository mRepo;
    public final ObservableField<UIState> uiState = new ObservableField<>(UIState.LOADING);

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

}
