package com.enpassio.databindingwithnewsapi.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public ArticleListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(
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
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mViewModel.getArticleList().observe(this, articles -> {
            if (!articles.isEmpty()) {
                mAdapter.setArticleList(articles);
                Log.d(TAG, "articles are received. list size: " + articles.size());
            }
        });
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
