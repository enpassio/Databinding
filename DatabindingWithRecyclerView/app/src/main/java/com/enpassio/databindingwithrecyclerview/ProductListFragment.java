package com.enpassio.databindingwithrecyclerview;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithrecyclerview.databinding.FragmentListBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

public class ProductListFragment extends Fragment {

    public ProductListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);

        //Set recycler view
        ProductAdapter mAdapter = new ProductAdapter(ProductDataSource.getProductData());
        binding.productsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.productsRecyclerView.setAdapter(mAdapter);

        return binding.getRoot();
    }
}
