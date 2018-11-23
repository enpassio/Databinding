package com.enpassio.databindingwithrecyclerview.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithrecyclerview.model.Product;
import com.enpassio.databindingwithrecyclerview.utils.ProductDataSource;
import com.enpassio.databindingwithrecyclerview.R;
import com.enpassio.databindingwithrecyclerview.databinding.FragmentListBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

public class ProductListFragment extends Fragment implements ProductAdapter.ProductItemClickListener {

    static final String PRODUCT_KEY = "productKey";

    public ProductListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);

        //Set recycler view, with the hardcoded list that can be found in ProductDataSource.class
        ProductAdapter mAdapter = new ProductAdapter(ProductDataSource.getProductData(), this);
        binding.productsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.productsRecyclerView.setAdapter(mAdapter);

        return binding.getRoot();
    }

    @Override
    public void onProductItemClicked(Product product) {
        //When a product item is clicked, pass the product object to the detailsFragment.
        DetailsFragment frag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_KEY, product);
        frag.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder, frag)
                .addToBackStack(null)
                .commit();
    }
}
