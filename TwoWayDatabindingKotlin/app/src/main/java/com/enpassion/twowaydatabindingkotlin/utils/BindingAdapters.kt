package com.enpassion.twowaydatabindingkotlin.utils

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.data.Gender
import com.enpassion.twowaydatabindingkotlin.data.ProcurementType


@BindingAdapter("genderDrawable")
fun getGenderDrawable(imageView: ImageView, gender: Gender) {
    val resourceId = when (gender) {
        Gender.UNISEX -> R.drawable.ic_rainbow
        Gender.GIRL -> R.drawable.ic_girl
        Gender.BOY -> R.drawable.ic_boy
    }
    imageView.setImageResource(resourceId)
}

@BindingAdapter("stateDrawable")
fun getStateDrawable(imageView: ImageView, procurementType: ProcurementType?) {
    procurementType?.run {
        val resourceId = when (this) {
            ProcurementType.BOUGHT -> R.drawable.ic_money
            ProcurementType.RECEIVED -> R.drawable.ic_gift
        }
        imageView.setImageResource(resourceId)
    }
}

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if(visible) View.VISIBLE else View.GONE
}