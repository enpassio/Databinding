package com.enpassion.twowaydatabindingkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.data.ToyRepository


class AddToyViewModelFactory(private val mRepo: ToyRepository, private val mChosenToy: ToyEntry?) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddToyViewModel(mRepo, mChosenToy) as T
    }
}