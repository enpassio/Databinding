package com.enpassio.databindingwithrecyclerview.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.enpassio.databindingwithrecyclerview.model.Product;
import com.enpassio.databindingwithrecyclerview.R;
import com.enpassio.databindingwithrecyclerview.databinding.ItemProductBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> mProductList;
    private final ProductItemClickListener mListener;

    ProductAdapter(List<Product> productList, ProductItemClickListener listener) {
        mProductList =productList;
        mListener = listener;
    }


    @Override
    public ProductViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        ItemProductBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product,
                        parent, false);
        //Pass an item click listener to each item layout.
        binding.setProductItemClick(mListener);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //For each item, corresponding product object is passed to the binding
        holder.binding.setProduct(mProductList.get(position));
        //This is to force bindings to execute right away
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        //If list is null, return 0, otherwise return the size of the list
        return mProductList == null ? 0 : mProductList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        final ItemProductBinding binding;

        ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ProductItemClickListener {
        void onProductItemClicked(Product product);
    }
}
