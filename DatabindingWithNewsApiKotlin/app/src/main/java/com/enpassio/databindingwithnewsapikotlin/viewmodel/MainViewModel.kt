package com.enpassio.databindingwithnewsapikotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Intent
import android.databinding.ObservableBoolean
import android.net.Uri
import android.util.Log
import android.view.View
import com.enpassio.databindingwithnewsapikotlin.data.Article
import com.enpassio.databindingwithnewsapikotlin.data.NewsRepository

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    private val mRepo: NewsRepository = NewsRepository.getInstance()
    val isLoading = ObservableBoolean(true)
    val networkConnected = ObservableBoolean(true)

    /*If article list is not null, return it, otherwise claim the
    data from the repository and assign it to articleList*/
    var articleList: LiveData<List<Article>>? = null
        get() = field ?: mRepo.articles.also { field = it }

    //Chosen article will be set later when an item is selected
    var chosenArticle: Article? = null

    fun startFetching(){
        mRepo.startFetching()
    }

    fun showList(): Boolean {
        return networkConnected.get() && !isLoading.get()
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