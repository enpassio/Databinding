package com.enpassio.databindingwithrecyclerviewkotlin.utils

import com.enpassio.databindingwithrecyclerviewkotlin.R
import com.enpassio.databindingwithrecyclerviewkotlin.model.Product
import java.util.*


/**
 * Created by Greta Grigutė on 2018-12-23.
 */
object ProductDataSource {

    val productData: ArrayList<Product>
        get() {
            val productArrayList = ArrayList<Product>()
            productArrayList.add(Product("pencil", 5, R.drawable.pencil))
            productArrayList.add(Product("bottle", 150, R.drawable.bottle))
            productArrayList.add(Product("chair", 500, R.drawable.chair))
            productArrayList.add(Product("apple", 100, R.drawable.apple))
            productArrayList.add(Product("paper", 20, R.drawable.paper))
            productArrayList.add(Product("cup", 50, R.drawable.cup))
            productArrayList.add(Product("keyboard", 950, R.drawable.keyboard))
            productArrayList.add(Product("mobile", 2000, R.drawable.mobile))
            productArrayList.add(Product("bag", 500, R.drawable.bag))
            productArrayList.add(Product("bulb", 100, R.drawable.bulb))
            return productArrayList
        }
}