@file:JvmName("BindingUtils")
package com.enpassion.twowaydatabindingkotlin.utils

import android.databinding.BindingAdapter
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.enpassion.twowaydatabindingkotlin.R

@BindingAdapter("genderDrawable")
fun getGenderDrawable(imageView: ImageView, position: Int) {
    when (position) {
        0 -> imageView.setImageResource(R.drawable.ic_rainbow)
        1 -> imageView.setImageDrawable(
                ColorDrawable(ContextCompat.getColor(imageView.context, R.color.color_girls)))
        2 -> imageView.setImageDrawable(
                ColorDrawable(ContextCompat.getColor(imageView.context, R.color.color_boys)))
    }
}

@BindingAdapter("stateDrawable")
fun getStateDrawable(imageView: ImageView, buttonId: Int) {
    when (buttonId) {
        R.id.radioBtn_new -> imageView.setImageResource(R.drawable.ic_gift)
        R.id.radioBtn_used -> imageView.setImageResource(R.drawable.ic_recycle)
    }
}

fun attachCategories(categories: Map<String, Boolean>): String? {
    return categories.filter { it.value }
        .keys
        .joinToString(separator = ", ")
}