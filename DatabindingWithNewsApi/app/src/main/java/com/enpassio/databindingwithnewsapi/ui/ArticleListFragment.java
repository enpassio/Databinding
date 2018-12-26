package com.enpassio.databindingwithnewsapi.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithnewsapi.R;
import com.enpassio.databindingwithnewsapi.databinding.FragmentListBinding;
import com.enpassio.databindingwithnewsapi.model.Article;
import com.enpassio.databindingwithnewsapi.viewmodel.MainViewModel;

public class ArticleListFragment extends Fragment implements NewsAdapter.ArticleClickListener {

    private MainViewModel mViewModel;
    private NewsAdapter mAdapter;
    private static final String TAG = "ArticleListFragment";
    private FragmentListBinding binding;

    public ArticleListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);

        //Set recycler view, with the hardcoded list that can be found in ProductDataSource.class
        mAdapter = new NewsAdapter(this);
        binding.newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.newsRecyclerView.setAdapter(mAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkConnectionAndChooseAction();
    }

    private void checkConnectionAndChooseAction() {
        /*Check if there is network connection. If there is connection, subscribe to viewmodel,
         which in return will get the instance of the repository and starts fetching from the internet*/
        if (thereIsConnection()) {
            binding.setIsLoading(true);
            binding.setNetworkConnected(true);
            subscribeViewModel();
        } else {
            //If there is no network, show a snack bar for warning the user
            binding.setIsLoading(false);
            binding.setNetworkConnected(false);
            showSnack();
        }
    }

    private void showSnack() {
        //Show a snack bar for warning about the network connection and prompt user to try again with a button
        Snackbar snackbar = Snackbar
                .make(binding.mainContent, R.string.no_network_connection, Snackbar.LENGTH_INDEFINITE)
                //If user will click "Retry", we'll repeat the same process again
                .setAction(R.string.retry, view -> checkConnectionAndChooseAction())
                //Set the color of action button
                .setActionTextColor(getResources().getColor(R.color.colorAccent));
        //Set the background color of the snack bar
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    private void subscribeViewModel() {
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel.getArticleList().observe(this, articles -> {
            if (articles != null) {
                binding.setIsLoading(false);
                if (!articles.isEmpty()) {
                    mAdapter.setArticleList(articles);
                    Log.d(TAG, "articles are received. list size: " + articles.size());
                }
            }
        });
    }

    private boolean thereIsConnection() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // If there is a network connection, return true, otherwise false
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onArticleClicked(Article chosenArticle) {
        mViewModel.setChosenArticle(chosenArticle);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder, new ArticleDetailsFragment())
                .addToBackStack(null)
                .commit();
    }
}
