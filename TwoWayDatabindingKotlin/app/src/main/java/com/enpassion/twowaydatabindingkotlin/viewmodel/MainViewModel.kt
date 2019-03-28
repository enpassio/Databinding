package com.enpassion.twowaydatabindingkotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.databinding.ObservableBoolean
import com.enpassion.twowaydatabindingkotlin.ToyRepository
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.utils.provideRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepo: ToyRepository = provideRepository(application)

    val isLoading = ObservableBoolean(true)
    val isEmpty = ObservableBoolean(false)

    var shouldShowList : Boolean = false
        get() = !isEmpty.get() && !isLoading.get()
        private set

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