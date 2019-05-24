 package com.enpassio.databindingwithnewsapi.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.enpassio.databindingwithnewsapi.R;
import com.enpassio.databindingwithnewsapi.databinding.ItemBinding;
import com.enpassio.databindingwithnewsapi.model.Article;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> mArticleList;
    private final ArticleClickListener mListener;

    NewsAdapter(ArticleClickListener listener) {
        mListener = listener;
    }

    void setArticleList(List<Article> articleList) {
        mArticleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_news_article,
                        parent, false);

        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(mArticleList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        //If list is null, return 0, otherwise return the size of the list
        return mArticleList == null ? 0 : mArticleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        final ItemBinding binding;

        NewsViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Article currentArticle, ArticleClickListener clickListener){
            //For each item, corresponding product object is passed to the binding
            binding.setArticle(currentArticle);
            //Pass an item click listener to each item layout.
            binding.setArticleItemClick(clickListener);
            //This is to force bindings to execute right away
            binding.executePendingBindings();
        }
    }

    public interface ArticleClickListener {
        void onArticleClicked(Article chosenArticle);
    }
}
