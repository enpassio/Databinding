package com.enpassio.twowaydatabinding.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.enpassio.twowaydatabinding.data.ToyRepository;
import com.enpassio.twowaydatabinding.data.model.ToyEntry;
import com.enpassio.twowaydatabinding.data.model.UIState;
import com.enpassio.twowaydatabinding.utils.InjectorUtils;

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
