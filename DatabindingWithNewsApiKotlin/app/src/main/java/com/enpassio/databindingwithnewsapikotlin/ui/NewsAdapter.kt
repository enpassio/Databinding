package com.enpassio.databindingwithnewsapikotlin.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.enpassio.databindingwithnewsapikotlin.R
import com.enpassio.databindingwithnewsapikotlin.databinding.ItemBinding
import com.enpassio.databindingwithnewsapikotlin.model.Article


class NewsAdapter (private val mListener: ArticleClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var articleList: List<Article>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = DataBindingUtil
            .inflate<ItemBinding>(LayoutInflater.from(parent.context), R.layout.item_news_article,
                parent, false)
        //Pass an item click listener to each item layout.
        binding.articleItemClick = mListener
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //For each item, corresponding product object is passed to the binding
        holder.binding.article = articleList?.get(position)
        //This is to force bindings to execute right away
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return articleList?.size ?: 0
    }

    inner class NewsViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface ArticleClickListener {
        fun onArticleClicked(chosenArticle: Article)
    }
}