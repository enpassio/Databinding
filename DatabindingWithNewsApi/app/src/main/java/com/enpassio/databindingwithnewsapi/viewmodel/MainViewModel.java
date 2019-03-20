package com.enpassio.databindingwithnewsapi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.repository.NewsRepository;
import com.enpassio.databindingwithnewsapi.utils.NetworkUtils;
import com.enpassio.databindingwithnewsapi.utils.UIState;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Article>> mArticleList;
    private Article mChosenArticle;
    private final NewsRepository mRepo;
    private MutableLiveData<Boolean> showSnack = new MutableLiveData<>();
    public final ObservableField<UIState> uiState = new ObservableField<>(UIState.LOADING);

    public MainViewModel(@NonNull Application application) {
        super(application);
        //Passing the application context to the repository (not activity context!)
        mRepo = NewsRepository.getInstance();
        showSnack.setValue(false);
    }

    public LiveData<List<Article>> getArticleList() {
        if (mArticleList == null) {
            mArticleList = mRepo.getArticles();
            checkConnectionAndStartLoading();
        }
        return mArticleList;
    }

    public Article getChosenArticle() {
        return mChosenArticle;
    }

    public void setChosenArticle(Article chosenArticle) {
        mChosenArticle = chosenArticle;
    }

    public LiveData<Boolean> shouldShowSnack() {
        return showSnack;
    }

    /*If there is internet connection, start fetching data from the internet,
   otherwise show a snack for warning user*/
    public void checkConnectionAndStartLoading() {
        if (NetworkUtils.thereIsConnection(getApplication())) {
            /*If there is connection, start fetching and change uiState
            to LOADING. This will show a loading indicator*/
            uiState.set(UIState.LOADING);
            mRepo.startFetching();
            showSnack.setValue(false);
        } else {
            //If there is no connection, show a snack message to warn user
            showSnack.setValue(true);
            /*Unless there is some previously fetched data to show,
            set uiState to NETWORK_ERROR This will show an error message*/
            if(mArticleList.getValue() == null || mArticleList.getValue().isEmpty()){
                uiState.set(UIState.NETWORK_ERROR);
            }
        }
    }
    public void openWebSite(View view) {
        if (mChosenArticle == null) {
            return;
        }
        String articleUrl = mChosenArticle.getArticleUrl();
        Uri webUri = null;
        if (!TextUtils.isEmpty(articleUrl)) {
            //Parse string to uri
            try {
                webUri = Uri.parse(articleUrl);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            //Send an implicit intent to open the article in the browser
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            webIntent.setData(webUri);
            if (webIntent.resolveActivity(view.getContext().getPackageManager()) != null) {
                view.getContext().startActivity(webIntent);
            }
        }
    }
}
