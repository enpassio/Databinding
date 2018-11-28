package com.enpassio.databindingwithrecyclerview.ui;



import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithrecyclerview.model.Product;
import com.enpassio.databindingwithrecyclerview.utils.ProductDataSource;
import com.enpassio.databindingwithrecyclerview.R;
import com.enpassio.databindingwithrecyclerview.databinding.FragmentListBinding;

public class ProductListFragment extends Fragment implements ProductAdapter.ProductItemClickListener {

    static final String PRODUCT_KEY = "productKey";

    public ProductListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
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
