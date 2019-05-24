package com.enpassio.databindingwithrecyclerviewkotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enpassio.databindingwithrecyclerviewkotlin.R
import com.enpassio.databindingwithrecyclerviewkotlin.databinding.ItemProductBinding
import com.enpassio.databindingwithrecyclerviewkotlin.model.Product


/**
 * Created by Greta Grigutė on 2018-12-23.
 */
class ProductAdapter internal constructor(
    private val mProductList: List<Product>,
    private val mListener: ProductItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder = ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(mProductList[position], mListener)

    override fun getItemCount(): Int = mProductList.size

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(currentProduct: Product, listener : ProductItemClickListener){
            binding.product = currentProduct
            binding.productItemClick = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemProductBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.item_product,
                        parent, false)
                return ProductViewHolder(binding)
            }
        }
    }

    interface ProductItemClickListener {
        fun onProductItemClicked(product: Product)
    }
}