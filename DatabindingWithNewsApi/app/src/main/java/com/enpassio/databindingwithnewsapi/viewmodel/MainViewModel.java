package com.enpassio.databindingwithnewsapi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.repository.NewsRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Article>> mArticleList;
    private final MutableLiveData<Article> mChosenArticle = new MutableLiveData<>();
    private final NewsRepository mRepo;
    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableBoolean networkConnected = new ObservableBoolean();

    public MainViewModel(@NonNull Application application, NewsRepository.NetworkStateListener listener) {
        super(application);
        //Passing the application context to the repository (not activity context!)
        mRepo = NewsRepository.getInstance(this.getApplication(), listener);
        //Set default values
        isLoading.set(true);
        networkConnected.set(true);
    }

    public LiveData<List<Article>> getArticleList() {
        if (mArticleList == null) {
            mArticleList = mRepo.getArticles();
        }
        return mArticleList;
    }

    public LiveData<Article> getChosenArticle() {
        return mChosenArticle;
    }

    public void setChosenArticle(Article chosenArticle) {
        mChosenArticle.setValue(chosenArticle);
    }

    public void checkConnectionAndStartLoading() {
        mRepo.checkConnectionAndStartFetching();
    }

}
