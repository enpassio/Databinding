package com.enpassio.databindingwithnewsapikotlin.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enpassio.databindingwithnewsapikotlin.data.NewsRepository
import com.enpassio.databindingwithnewsapikotlin.model.Article
import com.enpassio.databindingwithnewsapikotlin.model.UIState
import com.enpassio.databindingwithnewsapikotlin.utils.thereIsConnection

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    /*UI state keeps track of the data loading state: LOADING, NETWORK_ERROR or SUCCESS
    This information is kept in a livedata inside view model, so that
    changes are automatically reflected in UI. */
    private val _uiState : MutableLiveData<UIState> = MutableLiveData()
    val uiState : LiveData<UIState>
        get() = _uiState

    fun setUiState(newState: UIState){
        _uiState.value = newState
    }

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
        _uiState.value = UIState.LOADING
        _showSnack.value = false
    }

    /*If there is internet connection, start fetching data from the internet,
    otherwise show a snack for warning user*/
    fun checkConnectionAndStartLoading() {
        if (thereIsConnection(getApplication())) {
            /*If there is connection, start fetching and change uiState
            to LOADING. This will show a loading indicator*/
            _uiState.value = UIState.LOADING
            NewsRepository.startFetching()
            _showSnack.value = false
        } else {
            //If there is no connection, show a snack message to warn user
            _showSnack.value = true
            /*Unless there is some previously fetched data to show,
            set uiState to NETWORK_ERROR This will show an error message*/
            if(articleList?.value.isNullOrEmpty()){
                _uiState.value = UIState.NETWORK_ERROR
            }
        }
    }
}