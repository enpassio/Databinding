package com.enpassio.databindingwithrecyclerview.utils

import android.databinding.BindingAdapter
import android.widget.ImageView

/**
 * Created by Greta Grigutė on 2018-12-23.
 */
object BindingAdapters {

    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, resourceId: Int) {
        //Get the drawable from the resource id and set it to the image view.
        view.setImageDrawable(view.context.resources.getDrawable(resourceId))
    }
}