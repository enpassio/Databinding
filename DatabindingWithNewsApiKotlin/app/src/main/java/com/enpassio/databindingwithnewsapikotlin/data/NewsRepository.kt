package com.enpassio.databindingwithnewsapikotlin.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import com.enpassio.databindingwithnewsapikotlin.model.Article
import com.enpassio.databindingwithnewsapikotlin.utils.fetchArticles


object NewsRepository{

    /*We need a mutable live data here, so that we can modify it, but we
    prefer to pass an immutable live data to the UI layer*/
    private val _articles = MutableLiveData<List<Article>?>()
    val articles: LiveData<List<Article>?>
        get() = _articles

    fun startFetching() {
        //Start fetching from the News Api in a background thread
        NewsAsyncTask().execute()
    }

    private class NewsAsyncTask : AsyncTask<Void, Void, List<Article>>() {

        override fun doInBackground(vararg voids: Void): List<Article>? {
            return fetchArticles()
        }

        override fun onPostExecute(list: List<Article>?) {
            if(list?.isNotEmpty() == true){
                _articles.value = list
                Log.d(TAG, "list size: " + list.size)
            }
        }
    }

    private const val TAG = "NewsRepository"
}
