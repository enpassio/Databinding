package com.enpassio.databindingwithnewsapikotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder = NewsViewHolder.from(parent)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder.bind(articleList?.get(position), mListener)

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return articleList?.size ?: 0
    }

    class NewsViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentArticle: Article?, clickListener: ArticleClickListener){
            binding.article = currentArticle
            binding.articleItemClick = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val binding = DataBindingUtil.inflate<ItemBinding>(LayoutInflater.from(parent.context),
                    R.layout.item_news_article, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }

    interface ArticleClickListener {
        fun onArticleClicked(chosenArticle: Article)
    }
}
