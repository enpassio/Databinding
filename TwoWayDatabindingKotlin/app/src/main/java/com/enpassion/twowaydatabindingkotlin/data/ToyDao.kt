package com.enpassion.twowaydatabindingkotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ToyDao {

    @get:Query("SELECT * FROM toys")
    val allToys: LiveData<List<ToyEntry>>

    @Query("SELECT * FROM toys WHERE toyId = :id")
    fun getChosenToy(id: Int): LiveData<ToyEntry>

    @Insert
    fun insertToy(toy: ToyEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateToyInfo(toy: ToyEntry)

    @Delete
    fun deleteToy(toy: ToyEntry)
}