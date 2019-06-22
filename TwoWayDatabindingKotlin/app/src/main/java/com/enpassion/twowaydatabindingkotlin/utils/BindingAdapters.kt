package com.enpassion.twowaydatabindingkotlin.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.data.Gender
import com.enpassion.twowaydatabindingkotlin.data.ProcurementType


@BindingAdapter("genderDrawable")
fun ImageView.getGenderDrawable(gender: Gender) {
    val resourceId = when (gender) {
        Gender.UNISEX -> R.drawable.ic_rainbow
        Gender.GIRL -> R.drawable.ic_girl
        Gender.BOY -> R.drawable.ic_boy
    }
    setImageResource(resourceId)
}

@BindingAdapter("stateDrawable")
fun ImageView.getStateDrawable(procurementType: ProcurementType?) {
    procurementType?.run {
        val resourceId = when (this) {
            ProcurementType.BOUGHT -> R.drawable.ic_money
            ProcurementType.RECEIVED -> R.drawable.ic_gift
        }
        setImageResource(resourceId)
    }
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}