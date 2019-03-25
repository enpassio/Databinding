package com.enpassio.databindingwithnewsapikotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.enpassio.databindingwithnewsapikotlin.data.Article
import com.enpassio.databindingwithnewsapikotlin.data.NewsRepository
import com.enpassio.databindingwithnewsapikotlin.utils.UIState
import com.enpassio.databindingwithnewsapikotlin.utils.thereIsConnection

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    /*UI state keeps track of the data loading state: LOADING, NETWORK_ERROR or SUCCESS
    This information is kept in an observable field inside view model, so that
    changes are automatically reflected in UI. */
    val uiState = ObservableField<UIState>(UIState.LOADING)

    /*If article list is not null, return it, otherwise claim the
   data from the repository, assign it to articleList and start fetching*/
    var articleList: LiveData<List<Article>?>? = null
        get() = field ?: NewsRepository.articles.also {
            field = it
            checkConnectionAndStartLoading()
        }

    //Chosen article will be set later when an item is selected from the list
    var chosenArticle: Article? = null

    //If there is no network and data can't be fetched, show a snackbar to the user
    private val _showSnack = MutableLiveData<Boolean>()
    val showSnack : LiveData<Boolean>
        get() = _showSnack

    init {
        _showSnack.value = false
    }

    /*If there is internet connection, start fetching data from the internet,
    otherwise show a snack for warning user*/
    fun checkConnectionAndStartLoading() {
        if (thereIsConnection(getApplication())) {
            /*If there is connection, start fetching and change uiState
            to LOADING. This will show a loading indicator*/
            uiState.set(UIState.LOADING)
            NewsRepository.startFetching()
            _showSnack.value = false
        } else {
            //If there is no connection, show a snack message to warn user
            _showSnack.value = true
            /*Unless there is some previously fetched data to show,
            set uiState to NETWORK_ERROR This will show an error message*/
            if(articleList?.value.isNullOrEmpty()){
                uiState.set(UIState.NETWORK_ERROR)
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}