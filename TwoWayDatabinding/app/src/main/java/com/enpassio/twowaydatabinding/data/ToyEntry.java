package com.enpassio.twowaydatabinding.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.enpassio.twowaydatabinding.BR;

import java.util.Map;

@Entity(tableName = "toys")
public class ToyEntry extends BaseObservable {

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

    @Bindable
    public String getToyName() {
        return toyName;
    }

    @Bindable
    public Map<String, Boolean> getCategories() {
        return categories;
    }

    @Bindable
    public int getGender() {
        return gender;
    }

    @Bindable
    public int getState() {
        return state;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
        notifyPropertyChanged(BR.toyName);
        Log.d(TAG, "toy name set");
    }

    public void setCategories(Map<String, Boolean> categories) {
        this.categories = categories;
        notifyPropertyChanged(BR.categories);
        Log.d(TAG, "categories set");
    }

    public void setGender(int gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
        Log.d(TAG, "gender set");
    }

    public void setState(int state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
        Log.d(TAG, "state set");
    }

    @Override
    public String toString() {
        return "ToyEntry{" +
                "toyId=" + toyId +
                ", toyName='" + toyName + '\'' +
                ", categories=" + categories.size() +
                ", gender=" + gender +
                ", state=" + state +
                '}';
    }
}
