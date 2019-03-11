package com.enpassio.databindingwithnewsapikotlin.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.enpassio.databindingwithnewsapikotlin.utils.fetchArticles
import com.enpassio.databindingwithnewsapikotlin.utils.thereIsConnection


class NewsRepository private constructor(private val mContext: Context, private val mListener: NetworkStateListener) {

    /*We need a mutable live data here, so that we can modify it, but we
    prefer to pass an immutable live data to the UI layer*/
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    fun checkConnectionAndStartFetching() {
        //If data is already there, no need to go over this process again
        if (articles.value?.isNotEmpty() == true) {
            return
        }
        if (thereIsConnection(mContext)) {
            //Pass network state to fragment
            mListener.onNetworkStateChanged(true)
            Log.d(TAG, "there is connection, start fetching")
            //Start fetching from the News Api in a background thread
            NewsAsyncTask().execute()
        } else {
            Log.d(TAG, "there is no connection")
            //Pass network state to fragment
            mListener.onNetworkStateChanged(false)
        }
    }

    private inner class NewsAsyncTask : AsyncTask<Void, Void, List<Article>>() {

        override fun doInBackground(vararg voids: Void): List<Article>? {
            return fetchArticles()
        }

        override fun onPostExecute(list: List<Article>) {
            _articles.value = list
            Log.d(TAG, "list size: " + list.size)
        }
    }

    interface NetworkStateListener {
        fun onNetworkStateChanged(connected: Boolean)
    }

    companion object {

        @Volatile private var sInstance: NewsRepository? = null
        private val TAG = "NewsRepository"

        fun getInstance(context: Context, listener: NetworkStateListener): NewsRepository {
            return sInstance ?: synchronized(NewsRepository::class.java) {
                sInstance ?: NewsRepository(context, listener).also { sInstance = it }
            }
        }
    }
}