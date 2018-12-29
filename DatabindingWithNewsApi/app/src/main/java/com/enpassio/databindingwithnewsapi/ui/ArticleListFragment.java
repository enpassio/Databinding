package com.enpassio.databindingwithnewsapi.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.enpassio.databindingwithnewsapi.repository.NewsRepository;
import com.enpassio.databindingwithnewsapi.viewmodel.MainViewModel;
import com.enpassio.databindingwithnewsapi.viewmodel.MainViewModelFactory;

public class ArticleListFragment extends Fragment implements NewsAdapter.ArticleClickListener, NewsRepository.NetworkStateListener {

    private MainViewModel mViewModel;
    private NewsAdapter mAdapter;
    private static final String TAG = "ArticleListFragment";
    private NewsListBinding binding;

    public ArticleListFragment() {
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
        //Set default states in binding implementation
        binding.included.setIsLoading(true);
        binding.included.setNetworkConnected(true);

        //Create a custom view model factory so that we can pass a listener to it
        MainViewModelFactory factory = new MainViewModelFactory(requireActivity().getApplication(), this);
        //Get the view model instance
        mViewModel = ViewModelProviders.of(requireActivity(), factory).get(MainViewModel.class);
        //Claim the list from the view model and observe the results
        mViewModel.getArticleList().observe(this, articles -> {
            if (articles != null && !articles.isEmpty()) {
                /*When articles are received, hide the loading indicator
                and pass the articles to the adapter*/
                binding.included.setIsLoading(false);
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
                .setAction(R.string.retry, view -> mViewModel.retryConnecting())
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
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder, new ArticleDetailsFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNetworkStateChanged(boolean isConnected) {
        /*If network is connected, set as "loading" until data arrives. If there
        is no connection, remove loading indicator and show no network image instead*/
        binding.included.setIsLoading(isConnected);

        /*Whether there is network or not, pass that information to binding implementation*/
        binding.included.setNetworkConnected(isConnected);

        //If there is no network, show a snack bar to warn the user.
        if (!isConnected) {
            showSnack();
        }
    }
}
