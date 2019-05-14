package com.enpassio.twowaydatabinding.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.enpassio.twowaydatabinding.data.ToyRepository;
import com.enpassio.twowaydatabinding.data.model.Gender;
import com.enpassio.twowaydatabinding.data.model.ToyEntry;

import java.util.HashMap;
import java.util.Map;

public class AddToyViewModel extends ViewModel {

    private ToyRepository mRepo;
    private ToyEntry mChosenToy;
    private ToyEntry mToyBeingModified;
    private boolean mIsEdit;
    public static final String WOODEN = "Wooden";
    public static final String ELECTRONIC = "Electronic";
    public static final String PLASTIC = "Plastic";
    public static final String PLUSH = "Plush";
    public static final String MUSICAL = "Musical";
    public static final String EDUCATIVE = "Educative";

    public AddToyViewModel(ToyRepository repo, ToyEntry chosenToy) {
        mRepo = repo;
        if(chosenToy != null){
            //This is edit case
            mToyBeingModified = chosenToy.copy();
            mChosenToy = chosenToy;
            mIsEdit = true;
        } else {
            /*This is for adding a new toy. We initialize a ToyEntry with default or null values
            This is because two-way databinding in the AddToyFragment is designed to
            register changes automatically, but it will need a toy object to register those changes.*/
            mToyBeingModified = getEmptyToy();
            mIsEdit = false;
        }
    }

    public ToyEntry getToyBeingModified() {
        return mToyBeingModified;
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
        return new ToyEntry("", categories, Gender.UNISEX, null);
    }

    public boolean isChanged(){
        boolean isChanged;
        if(mIsEdit){
            isChanged = !mToyBeingModified.equals(mChosenToy);
        } else {
            isChanged = !mToyBeingModified.equals(getEmptyToy());
        }
        return isChanged;
    }
}
