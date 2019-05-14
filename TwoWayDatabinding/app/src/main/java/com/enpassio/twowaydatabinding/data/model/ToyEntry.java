package com.enpassio.twowaydatabinding.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "toys")
public class ToyEntry implements Parcelable {

    private static final String TAG = "ToyEntry";

    @PrimaryKey(autoGenerate = true)
    private int toyId;
    private String toyName;
    private Map<String, Boolean> categories;
    private Gender gender;
    private ProcurementType procurementType;

    public ToyEntry(int toyId, String toyName, Map<String, Boolean> categories, Gender gender, ProcurementType procurementType) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.categories = categories;
        this.gender = gender;
        this.procurementType = procurementType;
    }

    @Ignore
    public ToyEntry(String toyName, Map<String, Boolean> categories, Gender gender, ProcurementType procurementType) {
        this.toyName = toyName;
        this.categories = categories;
        this.gender = gender;
        this.procurementType = procurementType;
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

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public void setCategories(Map<String, Boolean> categories) {
        this.categories = categories;
    }

    public ProcurementType getProcurementType() {
        return procurementType;
    }

    public void setProcurementType(ProcurementType procurementType) {
        this.procurementType = procurementType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ToyEntry copy(){
        Map<String, Boolean> newCategories = new HashMap<>(this.categories);
        return new ToyEntry(toyId, toyName, newCategories, gender, procurementType);
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if(!(obj instanceof ToyEntry)) return false;

        ToyEntry other = (ToyEntry) obj;

        return other.getToyName().equals(toyName)
            && other.getCategories().equals(categories)
            && other.getGender() == gender
            && other.getProcurementType() == procurementType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (toyName == null ? 0 : toyName.hashCode());
        hash = 31 * hash + (categories == null ? 0 : categories.hashCode());
        hash = 31 * hash + (gender == null ? 0 : gender.hashCode());
        hash = 31 * hash + (procurementType == null ? 0 : procurementType.hashCode());
        return hash;
    }

    protected ToyEntry(Parcel in) {
        toyId = in.readInt();
        toyName = in.readString();
        gender = (Gender) in.readValue(Gender.class.getClassLoader());
        procurementType = (ProcurementType) in.readValue(ProcurementType.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(toyId);
        dest.writeString(toyName);
        dest.writeValue(gender);
        dest.writeValue(procurementType);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ToyEntry> CREATOR = new Parcelable.Creator<ToyEntry>() {
        @Override
        public ToyEntry createFromParcel(Parcel in) {
            return new ToyEntry(in);
        }

        @Override
        public ToyEntry[] newArray(int size) {
            return new ToyEntry[size];
        }
    };
}
