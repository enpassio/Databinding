package com.enpassio.databindingwithrecyclerviewkotlin.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enpassio.databindingwithrecyclerviewkotlin.R
import com.enpassio.databindingwithrecyclerviewkotlin.databinding.FragmentListBinding
import com.enpassio.databindingwithrecyclerviewkotlin.model.Product
import com.enpassio.databindingwithrecyclerviewkotlin.utils.ProductDataSource

/**
 * Created by Greta Grigutė on 2018-12-23.
 */


class ProductListFragment : Fragment(), ProductAdapter.ProductItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(
                inflater, R.layout.fragment_list, container, false)

        //Set recycler view, with the hardcoded list that can be found in ProductDataSource.class
        val mAdapter = ProductAdapter(ProductDataSource.productData, this)
        binding.productsRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.productsRecyclerView.adapter = mAdapter

        return binding.root
    }

    override fun onProductItemClicked(product: Product) {
        //When a product item is clicked, pass the product object to the detailsFragment.
        val frag = DetailsFragment()
        val args = Bundle()
        args.putParcelable(PRODUCT_KEY, product)
        frag.arguments = args
        fragmentManager!!.beginTransaction()
                .replace(R.id.fragment_holder, frag)
                .addToBackStack(null)
                .commit()
    }

    companion object {

        internal val PRODUCT_KEY = "productKey"
    }
}