package com.enpassion.twowaydatabindingkotlin.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.data.ToyRepository


class AddToyViewModelFactory(private val mRepo: ToyRepository, private val mChosenToy: ToyEntry?) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddToyViewModel(mRepo, mChosenToy) as T
    }
}