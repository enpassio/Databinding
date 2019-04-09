@file:JvmName("BindingUtils")
package com.enpassion.twowaydatabindingkotlin.utils

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.enpassion.twowaydatabindingkotlin.R


private const val UNISEX = 0
private const val GIRL = 1
private const val BOY = 2

@BindingAdapter("genderDrawable")
fun getGenderDrawable(imageView: ImageView, position: Int) {
    val resourceId = when (position) {
        UNISEX -> R.drawable.ic_rainbow
        GIRL -> R.drawable.ic_girl
        BOY -> R.drawable.ic_boy
        else -> R.drawable.ic_rainbow
    }
    imageView.setImageResource(resourceId)
}

@BindingAdapter("stateDrawable")
fun getStateDrawable(imageView: ImageView, buttonId: Int) {
    when (buttonId) {
        R.id.radioBtn_bought -> imageView.setImageResource(R.drawable.ic_money)
        R.id.radioBtn_received -> imageView.setImageResource(R.drawable.ic_gift)
    }
}

fun attachCategories(categories: Map<String, Boolean>): String? {
    return categories.filter { it.value }
        .keys
        .joinToString(separator = ", ")
}

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if(visible) View.VISIBLE else View.GONE
}