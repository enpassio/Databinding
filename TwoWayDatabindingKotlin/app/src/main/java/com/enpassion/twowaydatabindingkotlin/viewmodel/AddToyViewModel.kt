package com.enpassion.twowaydatabindingkotlin.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.enpassion.twowaydatabindingkotlin.ToyRepository
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry


class AddToyViewModel(private val mRepo: ToyRepository, toyId: Int) : ViewModel() {
    var chosenToy: LiveData<ToyEntry>? = null
        private set
    var toyBeingModified: ToyEntry? = null
    private var mIsEdit: Boolean = false

    init {
        if (toyId >= 0) {
            //This is edit case
            chosenToy = mRepo.getChosenToy(toyId)
            mIsEdit = true
        } else {
            /*This is for adding a new toy. We initialize a ToyEntry with default or null values
            This is because two-way databinding in the AddToyFragment is designed to
            register changes automatically, but it will need a toy object to register those changes.*/
            toyBeingModified = initializeEmptyToy()
            mIsEdit = false
        }
    }

    private fun insertToy(toy: ToyEntry?) {
        mRepo.insertToy(toy!!)
    }

    private fun updateToy(toy: ToyEntry?) {
        mRepo.updateToy(toy!!)
    }

    fun saveToy() {
        if (!mIsEdit) {
            insertToy(toyBeingModified)
        } else {
            updateToy(toyBeingModified)
        }
    }

    private fun initializeEmptyToy(): ToyEntry {
        val categories = mutableMapOf(WOODEN to false, ELECTRONIC to false,
        PLASTIC to false, PLUSH to false, MUSICAL to false, EDUCATIVE to false)
        return ToyEntry(_categories = categories)
    }

    companion object {
        const val WOODEN = "Wooden"
        const val ELECTRONIC = "Electronic"
        const val PLASTIC = "Plastic"
        const val PLUSH = "Plush"
        const val MUSICAL = "Musical"
        const val EDUCATIVE = "Educative"
    }
}