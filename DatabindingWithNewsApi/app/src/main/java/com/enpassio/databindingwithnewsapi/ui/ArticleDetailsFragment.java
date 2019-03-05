package com.enpassio.databindingwithnewsapi.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithnewsapi.R;
import com.enpassio.databindingwithnewsapi.databinding.FragmentDetailsBinding;
import com.enpassio.databindingwithnewsapi.viewmodel.MainViewModel;

public class ArticleDetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

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

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Get an instance of view model and pass it to the binding implementation
        MainViewModel viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
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
}
