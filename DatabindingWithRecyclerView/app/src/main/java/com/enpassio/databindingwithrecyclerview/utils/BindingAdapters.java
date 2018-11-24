package com.enpassio.databindingwithrecyclerview.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, int resourceId) {
        //Get the drawable from the resource id and set it to the image view.
        view.setImageDrawable(view.getContext().getResources().getDrawable(resourceId));
    }
}
