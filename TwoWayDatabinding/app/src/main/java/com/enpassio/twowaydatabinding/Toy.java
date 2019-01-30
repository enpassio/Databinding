package com.enpassio.twowaydatabinding;

import java.util.List;

//TODO: extend from BaseOBservable
//TODO: Return to an entity for Room
public class Toy {

    private String toyName;
    private List<String> categories;
    private String gender;
    private String state;

    public Toy(String toyName, List<String> categories, String gender, String state) {
        this.toyName = toyName;
        this.categories = categories;
        this.gender = gender;
        this.state = state;
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
