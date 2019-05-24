package com.enpassio.databindingwithnewsapi.data;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.utils.NetworkUtils;

import java.util.List;

public class NewsRepository {

    private static volatile NewsRepository sInstance;
    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();
    private static final String TAG = "NewsRepository";

    private NewsRepository() {
        Log.d(TAG, "New instance created");
    }

    public static NewsRepository getInstance() {
        if (sInstance == null) {
            synchronized (NewsRepository.class) {
                if (sInstance == null) {
                    sInstance = new NewsRepository();
                }
            }
        }
        return sInstance;
    }

    public void startFetching() {
        //Start fetching from the News Api in a background thread
        new NewsAsyncTask().execute();
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
            if(list != null && !list.isEmpty()){
                articles.setValue(list);
            }
        }

    }
}
