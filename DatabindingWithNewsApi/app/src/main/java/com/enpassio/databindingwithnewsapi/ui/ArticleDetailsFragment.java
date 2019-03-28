package com.enpassio.databindingwithnewsapi.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithnewsapi.R;
import com.enpassio.databindingwithnewsapi.databinding.FragmentDetailsBinding;

import static android.support.constraint.Constraints.TAG;

public class ArticleDetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private MainViewModel viewModel;

    public ArticleDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);

        //These are for making up button work.
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.toolbar);
        setHasOptionsMenu(true);

        binding.detailsReadMore.setOnClickListener(v -> openWebSite());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Get an instance of view model and pass it to the binding implementation
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //This is for making up button in the toolbar behave like back button
        if (item.getItemId() == android.R.id.home) {
            FragmentManager fm = getFragmentManager();
            if (fm != null) {
                fm.popBackStack();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void openWebSite() {
        String articleUrl = viewModel.getChosenArticle().getArticleUrl();
        Uri webUri = null;
        if (!TextUtils.isEmpty(articleUrl)) {
            //Parse string to uri
            try {
                webUri = Uri.parse(articleUrl);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            //Send an implicit intent to open the article in the browser
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            webIntent.setData(webUri);
            if (webIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                requireActivity().startActivity(webIntent);
            }
        }
    }
}
