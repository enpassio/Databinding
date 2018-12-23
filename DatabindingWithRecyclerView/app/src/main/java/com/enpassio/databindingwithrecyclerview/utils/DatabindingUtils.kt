@file:JvmName("DatabindingUtils")
package com.enpassio.databindingwithrecyclerview.utils

import java.text.NumberFormat

/**
 * Created by Greta Grigutė on 2018-12-23.
 */


    //This method gets the price as an integer and adds a currency sign before it
   fun addCurrencySign(price: Int): String {
        return NumberFormat.getCurrencyInstance().format(price.toLong())
    }
