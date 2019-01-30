package com.enpassio.twowaydatabinding.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

//TODO: extend from BaseOBservable

@Entity(tableName = "toys")
public class ToyEntry {

    @PrimaryKey
    private int toyId;
    private String toyName;
    private List<String> categories;
    private String gender;
    private String state;

    public ToyEntry(int toyId, String toyName, List<String> categories, String gender, String state) {
        this.toyId = toyId;
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

    public List<String> getCategories() {
        return categories;
    }

    public String getGender() {
        return gender;
    }

    public String getState() {
        return state;
    }

    public void setToyId(int toyId) {
        this.toyId = toyId;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setState(String state) {
        this.state = state;
    }
}
