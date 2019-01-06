package com.enpassio.databindingwithrecyclerviewkotlin.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.enpassio.databindingwithrecyclerviewkotlin.R
import com.enpassio.databindingwithrecyclerviewkotlin.databinding.ItemProductBinding
import com.enpassio.databindingwithrecyclerviewkotlin.model.Product


/**
 * Created by Greta Grigutė on 2018-12-23.
 */
class ProductAdapter internal constructor(private val mProductList: List<Product>?, private val mListener: ProductItemClickListener) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ItemProductBinding = DataBindingUtil
                .inflate<ItemProductBinding>(LayoutInflater.from(parent.context), R.layout.item_product,
                        parent, false)
        //Pass an item click listener to each item layout.
        binding.productItemClick = mListener
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        //For each item, corresponding product object is passed to the binding
        holder.binding.product = mProductList!![position]
        //This is to force bindings to execute right away
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return mProductList?.size ?: 0
    }

    inner class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    interface ProductItemClickListener {
        fun onProductItemClicked(product: Product)
    }
}