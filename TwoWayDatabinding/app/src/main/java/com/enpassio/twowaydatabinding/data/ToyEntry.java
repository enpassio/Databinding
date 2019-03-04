package com.enpassio.twowaydatabinding.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "toys")
public class ToyEntry{

    private static final String TAG = "ToyEntry";

    @PrimaryKey(autoGenerate = true)
    private int toyId;
    private String toyName;
    private Map<String, Boolean> categories;
    private int gender;
    private int state;

    public ToyEntry(int toyId, String toyName, Map<String, Boolean> categories, int gender, int state) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.categories = categories;
        this.gender = gender;
        this.state = state;
    }

    @Ignore
    public ToyEntry(String toyName, Map<String, Boolean> categories, int gender, int state) {
        this.toyName = toyName;
        this.categories = categories;
        this.gender = gender;
        this.state = state;
    }

    public int getToyId() {
        return toyId;
    }

    public String getToyName() {
        return toyName;
    }

    public Map<String, Boolean> getCategories() {
        return categories;
    }

    public int getGender() {
        return gender;
    }

    public int getState() {
        return state;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public void setCategories(Map<String, Boolean> categories) {
        this.categories = categories;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ToyEntry copy(){
        Map<String, Boolean> newCategories = new HashMap<>(this.categories);
        return new ToyEntry(toyId, toyName, newCategories, gender, state);
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if(!(obj instanceof ToyEntry)) return false;

        ToyEntry other = (ToyEntry) obj;

        return other.getToyName().equals(toyName)
            && other.getCategories().equals(categories)
            && other.getGender() == gender
            && other.getState() == state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (toyName == null ? 0 : toyName.hashCode());
        hash = 31 * hash + (categories == null ? 0 : categories.hashCode());
        hash = 31 * hash + gender;
        hash = 31 * hash + state;
        return hash;
    }
}
