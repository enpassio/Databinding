package com.enpassio.databindingwithrecyclerview;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, int resourceId) {
        //Get the drawable from the resource id and set it to the image view.
        view.setImageDrawable(view.getContext().getResources().getDrawable(resourceId));
    }
}
