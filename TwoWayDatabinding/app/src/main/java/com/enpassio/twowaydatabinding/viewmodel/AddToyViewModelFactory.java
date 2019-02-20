package com.enpassio.twowaydatabinding.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.enpassio.twowaydatabinding.ToyRepository;

public class AddToyViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ToyRepository mRepo;
    private int mToyId;

    public AddToyViewModelFactory(ToyRepository repo, int toyId) {
        mRepo = repo;
        mToyId = toyId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddToyViewModel(mRepo, mToyId);
    }
}

