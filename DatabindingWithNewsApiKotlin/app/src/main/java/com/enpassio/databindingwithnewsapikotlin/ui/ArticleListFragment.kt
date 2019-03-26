package com.enpassio.databindingwithnewsapikotlin.ui


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enpassio.databindingwithnewsapikotlin.R
import com.enpassio.databindingwithnewsapikotlin.model.Article
import com.enpassio.databindingwithnewsapikotlin.model.UIState


class ArticleListFragment : Fragment(), NewsAdapter.ArticleClickListener{

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
            inflater, R.layout.app_bar_main, container, false)

        //Set adapter, divider and default animator to the recycler view
        mAdapter = NewsAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)

        with(binding.included.newsRecyclerView){
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
            if(shouldShowSnack == true){
                showSnack()
            }
        })
    }

    private fun showSnack() {
        //Show a snack bar for warning about the network connection and prompt user to try again with a button
        val snackbar = Snackbar
            .make(binding.mainContent, R.string.no_network_connection, Snackbar.LENGTH_INDEFINITE)
            /*If user will click "Retry", we'll check the connection again,
                and fetch the news, if there is network this time. Otherwise, snack will be shown again.*/
            .setAction(R.string.retry) { mViewModel.checkConnectionAndStartLoading() }
            //Set the color of action button
            .setActionTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        //Set the background color of the snack bar
        snackbar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        snackbar.show()
    }

    override fun onArticleClicked(chosenArticle: Article) {
        /*When an article from the list is clicked, pass that article to the viewmodel
        as the selected article. Than launch details fragment. Note that you don't
        need to pass the article in the bundle, since the details fragment will get the
        selected item from the same view model. */
        mViewModel.chosenArticle = chosenArticle
            fragmentManager?.run {
                beginTransaction()
                    .replace(R.id.fragment_holder, ArticleDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
    }

    companion object {
        private const val TAG = "ArticleListFragment"
    }
}