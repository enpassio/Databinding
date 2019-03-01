package com.enpassion.twowaydatabindingkotlin.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.enpassion.twowaydatabindingkotlin.ToyRepository


class AddToyViewModelFactory(private val mRepo: ToyRepository, private val mToyId: Int) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddToyViewModel(mRepo, mToyId) as T
    }
}