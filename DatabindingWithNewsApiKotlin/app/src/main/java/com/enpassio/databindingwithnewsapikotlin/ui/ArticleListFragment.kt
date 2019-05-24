package com.enpassio.databindingwithnewsapikotlin.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.enpassio.databindingwithnewsapikotlin.R
import com.enpassio.databindingwithnewsapikotlin.databinding.NewsListBinding
import com.enpassio.databindingwithnewsapikotlin.model.Article
import com.enpassio.databindingwithnewsapikotlin.model.UIState
import org.jetbrains.anko.design.indefiniteSnackbar


class ArticleListFragment : Fragment(), NewsAdapter.ArticleClickListener {

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    private lateinit var mAdapter: NewsAdapter
    private lateinit var binding: NewsListBinding

    init {
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.app_bar_main, container, false
        )

        //Set adapter, divider and default animator to the recycler view
        mAdapter = NewsAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(),
            LinearLayoutManager.VERTICAL
        )

        with(binding.included.newsRecyclerView) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Get the view model instance and pass it to the binding implementation
        binding.included.uiState = mViewModel.uiState

        //When using livedata inside binding implementation, we should specify the lifecycle owner
        binding.lifecycleOwner = this.viewLifecycleOwner

        //Claim the list from the view model and observe the results
        mViewModel.articleList?.observe(this, Observer { articles ->
            if (!articles.isNullOrEmpty()) {
                /*When articles are received, pass the articles to the adapter
                And change uiState to SUCCESS. This will hide the loading indicator and show the list.*/
                mViewModel.setUiState(UIState.SUCCESS)
                mAdapter.articleList = articles
                Log.d(TAG, "articles are received. list size: " + articles.size)
            }
        })

        mViewModel.showSnack.observe(this, Observer { shouldShowSnack ->
            if (shouldShowSnack == true) {
                //Show a snack bar for warning about the network connection and prompt user to try again with a button
                binding.mainContent.indefiniteSnackbar(R.string.no_network_connection, R.string.retry) {
                    mViewModel.checkConnectionAndStartLoading()
                }
            }
        })
    }

    override fun onArticleClicked(chosenArticle: Article) {
        /*When an article from the list is clicked, pass that article to the viewmodel
        as the selected article. Than launch details fragment. Note that you don't
        need to pass the article in the bundle, since the details fragment will get the
        selected item from the same view model. */
        mViewModel.chosenArticle = chosenArticle
        fragmentManager?.transaction {
            replace(R.id.fragment_holder, ArticleDetailsFragment())
            addToBackStack(null)
        }
    }

    companion object {
        private const val TAG = "ArticleListFragment"
    }
}