package com.enpassio.databindingwithnewsapi;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class NewsRepository {

    private static NewsRepository sInstance;
    private static final String TAG = "NewsRepository";

    private NewsRepository() {
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

    private static class NewsAsyncTask extends AsyncTask<Void, Void, List<Article>> {

        @Override
        protected List<Article> doInBackground(Void... voids) {
            return NetworkUtils.fetchArticles();
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            for (Article article : articles) {
                Log.d(TAG, "article: " + article.toString());
            }
        }
    }


}
