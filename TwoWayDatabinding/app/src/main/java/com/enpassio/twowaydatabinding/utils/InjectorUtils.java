package com.enpassio.twowaydatabinding.utils;

import android.content.Context;

import com.enpassio.twowaydatabinding.ToyRepository;
import com.enpassio.twowaydatabinding.data.ToyDatabase;
import com.enpassio.twowaydatabinding.viewmodel.MainViewModelFactory;

public class InjectorUtils {

    public static ToyRepository provideRepository(Context context){
        AppExecutors executors = AppExecutors.getInstance();
        ToyDatabase db = ToyDatabase.getInstance(context);
        return ToyRepository.getInstance(db, executors);
    }

    public static MainViewModelFactory provideMainListFactory(Context context){
        ToyRepository repository = provideRepository(context);
        return new MainViewModelFactory(repository);
    }
}
