package com.enpassio.databindingwithrecyclerviewkotlin.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Greta Grigutė on 2018-12-22.
 */
data class Product(
        // Databinding need getters to get the fields we declared in xml layout.
        // Kotlin data class creates those for us.
        val productName: String?,
        val productPrice: Int,
        val productImage: Int):Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productName)
        parcel.writeInt(productPrice)
        parcel.writeInt(productImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}