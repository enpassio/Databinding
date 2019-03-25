package com.enpassio.databindingwithnewsapi.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.enpassio.databindingwithnewsapi.data.NewsRepository;
import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.model.UIState;
import com.enpassio.databindingwithnewsapi.utils.NetworkUtils;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Article>> mArticleList;
    private Article mChosenArticle;
    private final NewsRepository mRepo;
    private MutableLiveData<Boolean> showSnack = new MutableLiveData<>();
    public final MutableLiveData<UIState> uiState = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        //Passing the application context to the repository (not activity context!)
        mRepo = NewsRepository.getInstance();
        uiState.setValue(UIState.LOADING);
        showSnack.setValue(false);
    }

    LiveData<List<Article>> getArticleList() {
        if (mArticleList == null) {
            mArticleList = mRepo.getArticles();
            checkConnectionAndStartLoading();
        }
        return mArticleList;
    }

    public Article getChosenArticle() {
        return mChosenArticle;
    }

    void setChosenArticle(Article chosenArticle) {
        mChosenArticle = chosenArticle;
    }

    LiveData<Boolean> shouldShowSnack() {
        return showSnack;
    }

    /*If there is internet connection, start fetching data from the internet,
   otherwise show a snack for warning user*/
    void checkConnectionAndStartLoading() {
        if (NetworkUtils.thereIsConnection(getApplication())) {
            /*If there is connection, start fetching and change uiState
            to LOADING. This will show a loading indicator*/
            uiState.setValue(UIState.LOADING);
            mRepo.startFetching();
            showSnack.setValue(false);
        } else {
            //If there is no connection, show a snack message to warn user
            showSnack.setValue(true);
            /*Unless there is some previously fetched data to show,
            set uiState to NETWORK_ERROR This will show an error message*/
            if(mArticleList.getValue() == null || mArticleList.getValue().isEmpty()){
                uiState.setValue(UIState.NETWORK_ERROR);
            }
        }
    }
}
