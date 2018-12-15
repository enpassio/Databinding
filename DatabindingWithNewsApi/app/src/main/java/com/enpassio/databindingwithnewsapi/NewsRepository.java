package com.enpassio.databindingwithnewsapi;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class NewsRepository {

    private static NewsRepository sInstance;
    private MutableLiveData<List<Article>> articles = new MutableLiveData<>();
    private static final String TAG = "NewsRepository";

    private NewsRepository() {
        Log.d(TAG, "New instance created");
        new NewsAsyncTask().execute();
    }

    public static NewsRepository getInstance() {
        if (sInstance == null) {
            synchronized (NewsRepository.class) {
                sInstance = new NewsRepository();
            }
        }
        return sInstance;
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


}
