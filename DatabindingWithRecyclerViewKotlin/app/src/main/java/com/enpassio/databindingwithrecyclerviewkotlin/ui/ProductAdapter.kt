package com.enpassio.databindingwithrecyclerviewkotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.enpassio.databindingwithrecyclerviewkotlin.R
import com.enpassio.databindingwithrecyclerviewkotlin.databinding.ItemProductBinding
import com.enpassio.databindingwithrecyclerviewkotlin.model.Product


/**
 * Created by Greta Grigutė on 2018-12-23.
 */
class ProductAdapter internal constructor(
    private val mProductList: List<Product>,
    private val mListener: ProductItemClickListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ItemProductBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), R.layout.item_product,
                parent, false
            )
        //Pass an item click listener to each item layout.
        binding.productItemClick = mListener
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        //For each item, corresponding product object is passed to the binding
        holder.binding.product = mProductList[position]
        //This is to force bindings to execute right away
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    inner class ProductViewHolder(val binding: ItemProductBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    interface ProductItemClickListener {
        fun onProductItemClicked(product: Product)
    }
}