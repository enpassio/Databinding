package com.enpassio.twowaydatabinding.utils;

import android.content.Context;

import com.enpassio.twowaydatabinding.data.ToyDatabase;
import com.enpassio.twowaydatabinding.data.ToyRepository;

public class InjectorUtils {

    public static ToyRepository provideRepository(Context context){
        AppExecutors executors = AppExecutors.getInstance();
        ToyDatabase db = ToyDatabase.getInstance(context);
        return ToyRepository.getInstance(db, executors);
    }
}
