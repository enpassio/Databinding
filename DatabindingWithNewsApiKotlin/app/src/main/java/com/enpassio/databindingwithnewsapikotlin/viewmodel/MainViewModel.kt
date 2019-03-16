package com.enpassio.databindingwithnewsapikotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import android.util.Log
import android.view.View
import com.enpassio.databindingwithnewsapikotlin.data.Article
import com.enpassio.databindingwithnewsapikotlin.data.NewsRepository
import com.enpassio.databindingwithnewsapikotlin.utils.UIState

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    private val mRepo: NewsRepository = NewsRepository.getInstance()

    /*UI state keeps track of the data loading state: LOADING, NETWORK_ERROR or SUCCESS
    This information is kept in an observable field inside view model, so that
    changes are automatically reflected in UI. */
    val uiState = ObservableField<UIState>(UIState.LOADING)

    /*If article list is not null, return it, otherwise claim the
    data from the repository and assign it to articleList*/
    var articleList: LiveData<List<Article>>? = null
        get() = field ?: mRepo.articles.also { field = it }

    //Chosen article will be set later when an item is selected
    var chosenArticle: Article? = null

    fun startFetching(){
        mRepo.startFetching()
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