package com.enpassio.twowaydatabinding.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.enpassio.twowaydatabinding.ToyRepository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final ToyRepository mRepo;

    public MainViewModelFactory(ToyRepository toyRepository) {
        mRepo = toyRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(mRepo);
    }
}

