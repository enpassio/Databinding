package com.enpassio.databindingwithrecyclerview.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithrecyclerview.R;
import com.enpassio.databindingwithrecyclerview.databinding.FragmentDetailsBinding;
import com.enpassio.databindingwithrecyclerview.model.Product;

import static com.enpassio.databindingwithrecyclerview.ui.ProductListFragment.PRODUCT_KEY;

public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDetailsBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);

        //These are for making up button work. Not related to databinding.
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            /*Once we get the chosen product from the bundle,
            we pass it to the binding implementation*/
            Product chosenProduct = bundle.getParcelable(PRODUCT_KEY);
            binding.setProduct(chosenProduct);
        }

        return binding.getRoot();
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
