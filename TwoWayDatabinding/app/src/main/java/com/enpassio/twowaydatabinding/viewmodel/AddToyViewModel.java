package com.enpassio.twowaydatabinding.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.enpassio.twowaydatabinding.ToyRepository;
import com.enpassio.twowaydatabinding.data.ToyEntry;

import java.util.HashMap;
import java.util.Map;

public class AddToyViewModel extends ViewModel {

    private ToyRepository mRepo;
    private LiveData<ToyEntry> mChosenToy;
    private ToyEntry mToyBeingModified;
    private boolean mIsEdit;
    public static final String WOODEN = "Wooden";
    public static final String ELECTRONIC = "Electronic";
    public static final String PLASTIC = "Plastic";
    public static final String PLUSH = "Plush";
    public static final String MUSICAL = "Musical";
    public static final String EDUCATIVE = "Educative";

    public AddToyViewModel(ToyRepository repo, int toyId) {
        mRepo = repo;
        if(toyId >= 0){
            //This is edit case
            mChosenToy = mRepo.getChosenToy(toyId);
            mIsEdit = true;
        } else {
            /*This is for adding a new toy. We initialize a ToyEntry with default or null values
            This is because two-way databinding in the AddToyFragment is designed to
            register changes automatically, but it will need a toy object to register those changes.*/
            mToyBeingModified = getEmptyToy();
            mIsEdit = false;
        }
    }

    public LiveData<ToyEntry> getChosenToy() {
        return mChosenToy;
    }

    public ToyEntry getToyBeingModified() {
        return mToyBeingModified;
    }

    public void setToyBeingModified(ToyEntry toy) {
        mToyBeingModified = toy.copy();
    }

    private void insertToy(final ToyEntry toy){
        mRepo.insertToy(toy);
    }

    private void updateToy(final ToyEntry toy){
        mRepo.updateToy(toy);
    }

    public void saveToy(){
        if(!mIsEdit){
            insertToy(mToyBeingModified);
        } else {
            updateToy(mToyBeingModified);
        }
    }

    private ToyEntry getEmptyToy() {
        Map<String, Boolean> categories = new HashMap<>();
        categories.put(WOODEN, false);
        categories.put(ELECTRONIC, false);
        categories.put(PLASTIC, false);
        categories.put(PLUSH, false);
        categories.put(MUSICAL, false);
        categories.put(EDUCATIVE, false);
        return new ToyEntry("", categories, 0, 0);
    }

    public boolean isChanged(){
        boolean isChanged;
        if(mIsEdit){
            isChanged = !mToyBeingModified.equals(mChosenToy.getValue());
        } else {
            isChanged = !mToyBeingModified.equals(getEmptyToy());
        }
        return isChanged;
    }
}
