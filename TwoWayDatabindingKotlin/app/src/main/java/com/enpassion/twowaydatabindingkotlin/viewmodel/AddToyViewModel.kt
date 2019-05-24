package com.enpassion.twowaydatabindingkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.data.ToyRepository


class AddToyViewModel(private val mRepo: ToyRepository, private val chosenToy: ToyEntry?) : ViewModel() {

    val toyBeingModified: ToyEntry

    private var mIsEdit: Boolean = false

    init {
        if (chosenToy != null) {
            //This is edit case
            toyBeingModified = chosenToy.copy()
            mIsEdit = true
        } else {
            /*This is for adding a new toy. We initialize a ToyEntry with default or null values
            This is because two-way databinding in the AddToyFragment is designed to
            register changes automatically, but it will need a toy object to register those changes.*/
            toyBeingModified = emptyToy
            mIsEdit = false
        }
    }

    private fun insertToy(toy: ToyEntry) {
        mRepo.insertToy(toy)
    }

    private fun updateToy(toy: ToyEntry) {
        mRepo.updateToy(toy)
    }

    fun saveToy() {
        if (!mIsEdit) {
            insertToy(toyBeingModified)
        } else {
            updateToy(toyBeingModified)
        }
    }

    private val emptyToy: ToyEntry
        get() {
            val categories = mutableMapOf(
                WOODEN to false, ELECTRONIC to false,
                PLASTIC to false, PLUSH to false, MUSICAL to false, EDUCATIVE to false
            )
            return ToyEntry(toyName = "", categories = categories)
        }


   var isChanged : Boolean = false
        get() = if(mIsEdit) toyBeingModified != chosenToy
                    else toyBeingModified != emptyToy
        private set

    companion object {
        const val WOODEN = "Wooden"
        const val ELECTRONIC = "Electronic"
        const val PLASTIC = "Plastic"
        const val PLUSH = "Plush"
        const val MUSICAL = "Musical"
        const val EDUCATIVE = "Educative"
    }
}