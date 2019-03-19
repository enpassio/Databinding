package com.enpassio.databindingwithnewsapikotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.util.Log
import android.view.View
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

    //Get the data from the repository and assign it to articleList
    var articleList: LiveData<List<Article>?> = NewsRepository.articles

    //Chosen article will be set later when an item is selected from the list
    var chosenArticle: Article? = null

    //If there is no network and data can't be fetched, show a snackbar to the user
    private val _showSnack = MutableLiveData<Boolean>()
    val showSnack : LiveData<Boolean>
        get() = _showSnack

    init {
        /*We don't check connection and restart loading if data is already there.
        You might ask if data could be already there when viewModel is just initialized
        Answer is yes, if user quits the app from back button, activities and viewModels are destroyed,
        but static instance of singleton repository survives.*/
        if(articleList.value.isNullOrEmpty()){
            checkConnectionAndStartLoading()
        }
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
            /*If there is no connection, set uiState to NETWORK_ERROR
            This will show an error message*/
            uiState.set(UIState.NETWORK_ERROR)
            _showSnack.value = true
        }
    }

    fun openWebSite(view: View) {
        val articleUrl = chosenArticle?.articleUrl
        if (!articleUrl.isNullOrEmpty()) {
            //Parse string to uri
            var webUri: Uri? = null
            try {
                webUri = Uri.parse(articleUrl)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }

            //Send an implicit intent to open the article in the browser
            val webIntent = Intent(Intent.ACTION_VIEW)
            with(webIntent) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                data = webUri
                resolveActivity(view.context.packageManager)?.let {
                    view.context.startActivity(this)
                }
            }

            if (webIntent.resolveActivity(view.context.packageManager) != null) {
                view.context.startActivity(webIntent)
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}