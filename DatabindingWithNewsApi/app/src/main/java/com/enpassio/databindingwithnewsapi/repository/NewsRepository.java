package com.enpassio.databindingwithnewsapi.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.utils.NetworkUtils;

import java.util.List;

public class NewsRepository {

    private static NewsRepository sInstance;
    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();
    private static final String TAG = "NewsRepository";
    private final Context mContext;
    private final NetworkStateListener mListener;

    private NewsRepository(Context context, NetworkStateListener listener) {
        Log.d(TAG, "New instance created");
        mContext = context;
        mListener = listener;
        checkConnectionAndStartFetching();
    }

    public static NewsRepository getInstance(Context context, NetworkStateListener listener) {
        if (sInstance == null) {
            synchronized (NewsRepository.class) {
                sInstance = new NewsRepository(context, listener);
            }
        }
        return sInstance;
    }

    public void checkConnectionAndStartFetching() {
        if (NetworkUtils.thereIsConnection(mContext)) {
            //Pass network state to fragment
            mListener.onNetworkStateChanged(true);
            Log.d(TAG, "there is connection, start fetching");
            //Start fetching from the News Api in a background thread
            new NewsAsyncTask().execute();
        } else {
            Log.d(TAG, "there is no connection");
            //Pass network state to fragment
            mListener.onNetworkStateChanged(false);
        }
    }

    public LiveData<List<Article>> getArticles() {
        return articles;
    }

    private class NewsAsyncTask extends AsyncTask<Void, Void, List<Article>> {

        @Override
        protected List<Article> doInBackground(Void... voids) {
            return NetworkUtils.fetchArticles();
        }

        @Override
        protected void onPostExecute(List<Article> list) {
            articles.setValue(list);
            Log.d(TAG, "list size: " + list.size());
        }
    }

    public interface NetworkStateListener {
        void onNetworkStateChanged(boolean connected);
    }
}
