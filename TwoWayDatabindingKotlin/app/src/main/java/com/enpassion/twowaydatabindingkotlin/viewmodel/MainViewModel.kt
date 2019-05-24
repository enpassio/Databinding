package com.enpassion.twowaydatabindingkotlin.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.data.ToyRepository
import com.enpassion.twowaydatabindingkotlin.data.UIState
import com.enpassion.twowaydatabindingkotlin.utils.provideRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepo: ToyRepository = provideRepository(application)

    val uiState = ObservableField<UIState>(UIState.LOADING)

    var toyList: LiveData<List<ToyEntry>>? = null
        get() {
            return field ?: mRepo.toyList.also { field = it }
        }

    fun insertToy(toy: ToyEntry) {
        mRepo.insertToy(toy)
    }

    fun deleteToy(toy: ToyEntry) {
        mRepo.deleteToy(toy)
    }


}