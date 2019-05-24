package com.enpassio.databindingwithnewsapikotlin.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.enpassio.databindingwithnewsapikotlin.R
import com.enpassio.databindingwithnewsapikotlin.databinding.FragmentDetailsBinding


class ArticleDetailsFragment : androidx.fragment.app.Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        //These are for making up button work.
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)

        binding.detailsReadMore.setOnClickListener { openWebSite() }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Get an instance of view model and pass it to the binding implementation
        binding.viewModel = viewModel
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //This is for making up button in the toolbar behave like back button
        if (item.itemId == android.R.id.home) {
            fragmentManager?.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openWebSite() {
        val articleUrl = viewModel.chosenArticle?.articleUrl
        if (!articleUrl.isNullOrEmpty()) {
            //Parse string to uri
            var webUri: Uri? = null
            try {
                webUri = Uri.parse(articleUrl)
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }

            //Send an implicit intent to open the article in the browser
            val webIntent = Intent(Intent.ACTION_VIEW)
            with(webIntent) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                data = webUri
                resolveActivity(requireActivity().packageManager)?.let {
                    requireActivity().startActivity(this)
                }
            }
        }
    }

    companion object {
        const val TAG = "ArticleDetailsFragment"
    }
}