package com.enpassio.databindingwithnewsapi.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithnewsapi.R;
import com.enpassio.databindingwithnewsapi.databinding.NewsListBinding;
import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.utils.UIState;
import com.enpassio.databindingwithnewsapi.viewmodel.MainViewModel;

public class ArticleListFragment extends Fragment implements NewsAdapter.ArticleClickListener{

    private MainViewModel mViewModel;
    private NewsAdapter mAdapter;
    private static final String TAG = "ArticleListFragment";
    private NewsListBinding binding;

    public ArticleListFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.app_bar_main, container, false);

        //Set adapter, divider and default animator to the recycler view
        mAdapter = new NewsAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(),
                LinearLayoutManager.VERTICAL);
        binding.included.newsRecyclerView.addItemDecoration(dividerItemDecoration);
        binding.included.newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.included.newsRecyclerView.setAdapter(mAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get the view model instance and pass it to the binding implementation
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        binding.included.setViewModel(mViewModel);

        //Verify the connection and start loading from the api
        mViewModel.checkConnectionAndStartLoading();

        mViewModel.getConnectionStatus().observe(this, isConnected -> {
            /*If network is connected, set as "loading" until data arrives. If there
            is no connection, remove loading indicator and show no network image instead*/
            //If there is no network, show a snack bar to warn the user.
            if (!isConnected) {
                mViewModel.uiState.set(UIState.NETWORK_ERROR);
                showSnack();
            } else {
                mViewModel.uiState.set(UIState.LOADING);
            }
        });

        //Claim the list from the view model and observe the results
        mViewModel.getArticleList().observe(this, articles -> {
            if (articles != null && !articles.isEmpty()) {
                /*When articles are received, hide the loading indicator
                and pass the articles to the adapter*/
                mViewModel.uiState.set(UIState.SUCCESS);
                mAdapter.setArticleList(articles);
                Log.d(TAG, "articles are received. list size: " + articles.size());
            }
        });
    }

    private void showSnack() {
        //Show a snack bar for warning about the network connection and prompt user to try again with a button
        Snackbar snackbar = Snackbar
                .make(binding.mainContent, R.string.no_network_connection, Snackbar.LENGTH_INDEFINITE)
                /*If user will click "Retry", we'll check the connection again,
                and fetch the news, if there is network this time. Otherwise, snack will be shown again.*/
                .setAction(R.string.retry, view -> mViewModel.checkConnectionAndStartLoading())
                //Set the color of action button
                .setActionTextColor(getResources().getColor(R.color.colorAccent));
        //Set the background color of the snack bar
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void onArticleClicked(Article chosenArticle) {
        /*When an article from the list is clicked, pass that article to the viewmodel
        as the selected article. Than launch details fragment. Note that you don't
        need to pass the article in the bundle, since the details fragment will get the
        selected item from the same view model. */
        mViewModel.setChosenArticle(chosenArticle);
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_holder, new ArticleDetailsFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
