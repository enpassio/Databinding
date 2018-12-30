package com.enpassio.databindingwithnewsapi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.enpassio.databindingwithnewsapi.repository.NewsRepository;

/*We create this custom view model factory when we need to pass something
to the view model as an argument in its constructor.*/

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;
    private final NewsRepository.NetworkStateListener mListener;

    public MainViewModelFactory(@NonNull Application application, NewsRepository.NetworkStateListener listener) {
        mApplication = application;
        mListener = listener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(mApplication, mListener);
    }
}

