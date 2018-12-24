package com.enpassio.databindingwithnewsapi;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<Article>> mArticleList;
    private MutableLiveData<Article> mChosenArticle = new MutableLiveData<>();

    public MainViewModel() {
        mArticleList = NewsRepository.getInstance().getArticles();
    }

    public LiveData<List<Article>> getArticleList() {
        return mArticleList;
    }

    public LiveData<Article> getChosenArticle() {
        return mChosenArticle;
    }

    public void setChosenArticle(Article chosenArticle) {
        mChosenArticle.setValue(chosenArticle);
    }

}
