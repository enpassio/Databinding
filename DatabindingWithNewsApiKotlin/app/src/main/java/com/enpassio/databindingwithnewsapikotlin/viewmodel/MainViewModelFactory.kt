package com.enpassio.databindingwithnewsapikotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.enpassio.databindingwithnewsapikotlin.data.NewsRepository


class MainViewModelFactory(
    private val mApplication: Application,
    private val mListener: NewsRepository.NetworkStateListener
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mApplication, mListener) as T
    }
}