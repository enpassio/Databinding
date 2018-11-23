package com.enpassio.databindingwithrecyclerview.utils;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, int resourceId) {
        //This is for turning off default transformations
        RequestOptions options = new RequestOptions();
        options.dontTransform();

        Glide.with(view.getContext())
                .load(resourceId)
                .apply(options)
                .into(view);
    }

    /////////////// WITHOUT GLIDE: ////////////////////////

    /*@BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, int resourceId) {
        //Get the drawable from the resource id and set it to the image view.
        view.setImageDrawable(view.getContext().getResources().getDrawable(resourceId));
    }*/

}
