package com.enpassio.twowaydatabinding.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.enpassio.twowaydatabinding.data.model.ToyEntry;

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
