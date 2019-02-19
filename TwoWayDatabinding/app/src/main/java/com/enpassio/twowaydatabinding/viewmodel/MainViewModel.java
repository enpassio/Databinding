package com.enpassio.twowaydatabinding.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.enpassio.twowaydatabinding.ToyRepository;
import com.enpassio.twowaydatabinding.data.ToyEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainViewModel extends ViewModel {

    private LiveData<List<ToyEntry>> mToyList;
    private ToyRepository mRepo;
    public final ObservableBoolean isLoading = new ObservableBoolean(true);
    public final ObservableBoolean isEmpty = new ObservableBoolean(false);
    private ToyEntry mChosenToy;
    private boolean isEdit;
    public static final String WOODEN = "Wooden";
    public static final String ELECTRONIC = "Electronic";
    public static final String PLASTIC = "Plastic";
    public static final String PLUSH = "Plush";
    public static final String MUSICAL = "Musical";
    public static final String EDUCATIVE = "Educative";

    public MainViewModel(ToyRepository repository) {
        mRepo = repository;
        loadToyList();
    }

    private void loadToyList(){
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

    public ToyEntry getChosenToy() {
        return mChosenToy;
    }

    public void setChosenToy(ToyEntry toy) {
        mChosenToy = toy;
    }

    public void saveToy(){
        if(!isEdit){
            insertToy(mChosenToy);
        } else {
            updateToy(mChosenToy);
        }
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        if(!isEdit){
            //Create an initial toy object with a non-null map
            Map<String, Boolean> categories = new HashMap<>();
            categories.put(WOODEN, false);
            categories.put(ELECTRONIC, false);
            categories.put(PLASTIC, false);
            categories.put(PLUSH, false);
            categories.put(MUSICAL, false);
            categories.put(EDUCATIVE, false);
            mChosenToy = new ToyEntry(null, categories, 0, 0);
        }
    }
}
