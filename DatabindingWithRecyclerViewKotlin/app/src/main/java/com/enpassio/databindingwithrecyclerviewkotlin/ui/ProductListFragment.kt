package com.enpassio.databindingwithrecyclerviewkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.enpassio.databindingwithrecyclerviewkotlin.R
import com.enpassio.databindingwithrecyclerviewkotlin.databinding.FragmentListBinding
import com.enpassio.databindingwithrecyclerviewkotlin.model.Product
import com.enpassio.databindingwithrecyclerviewkotlin.utils.ProductDataSource

/**
 * Created by Greta Grigutė on 2018-12-23.
 */

const val PRODUCT_KEY = "productKey"

class ProductListFragment : androidx.fragment.app.Fragment(), ProductAdapter.ProductItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(
                inflater, R.layout.fragment_list, container, false)

        //Set recycler view, with the hardcoded list that can be found in ProductDataSource.class
        val mAdapter = ProductAdapter(ProductDataSource.productData, this)

        with(binding.productsRecyclerView){
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            adapter = mAdapter
        }

        return binding.root
    }

    override fun onProductItemClicked(product: Product) {
        //When a product item is clicked, pass the product object to the detailsFragment.
        val frag = DetailsFragment()
        val args = Bundle()
        args.putParcelable(PRODUCT_KEY, product)
        frag.arguments = args
        fragmentManager?.transaction {
            replace(R.id.fragment_holder, frag)
            addToBackStack(null)
        }
    }

}