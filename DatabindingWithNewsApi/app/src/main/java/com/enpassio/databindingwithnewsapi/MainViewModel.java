package com.enpassio.databindingwithnewsapi;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<Article>> mArticleList;

    public MainViewModel() {
        mArticleList = NewsRepository.getInstance().getArticles();
    }

    public LiveData<List<Article>> getArticleList() {
        return mArticleList;
    }

}
