package com.enpassio.databindingwithnewsapi.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.repository.NewsRepository;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Article>> mArticleList;
    private Article mChosenArticle;
    private final NewsRepository mRepo;
    public final ObservableBoolean isLoading = new ObservableBoolean(true);
    public final ObservableBoolean networkConnected = new ObservableBoolean(true);

    public MainViewModel(@NonNull Application application, NewsRepository.NetworkStateListener listener) {
        super(application);
        //Passing the application context to the repository (not activity context!)
        mRepo = NewsRepository.getInstance(this.getApplication(), listener);
    }

    public LiveData<List<Article>> getArticleList() {
        if (mArticleList == null) {
            mArticleList = mRepo.getArticles();
        }
        return mArticleList;
    }

    public Article getChosenArticle() {
        return mChosenArticle;
    }

    public void setChosenArticle(Article chosenArticle) {
        mChosenArticle = chosenArticle;
    }

    public void checkConnectionAndStartLoading() {
        mRepo.checkConnectionAndStartFetching();
    }

    public boolean showList() {
        return networkConnected.get() && !isLoading.get();
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
