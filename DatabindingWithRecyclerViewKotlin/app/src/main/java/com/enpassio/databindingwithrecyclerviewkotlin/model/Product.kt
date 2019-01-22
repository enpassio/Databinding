package com.enpassio.databindingwithrecyclerviewkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Greta Grigutė on 2018-12-22.
 */
@Parcelize
data class Product(
        // Databinding need getters to get the fields we declared in xml layout.
        // Kotlin data class creates those for us.
        val productName: String?,
        val productPrice: Int,
        val productImage: Int) : Parcelable
