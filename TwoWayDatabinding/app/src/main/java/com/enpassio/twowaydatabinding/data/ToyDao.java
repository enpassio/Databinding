package com.enpassio.twowaydatabinding.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ToyDao {

    @Query("SELECT * FROM toys")
    LiveData<List<ToyEntry>> getAllToys();

    @Query("SELECT * FROM toys WHERE toyId = :id")
    LiveData<ToyEntry> getChosenToy(int id);

    @Insert
    void insertToy(ToyEntry toy);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateToyInfo(ToyEntry toy);

    @Delete
    void deleteToy(ToyEntry toy);
}
