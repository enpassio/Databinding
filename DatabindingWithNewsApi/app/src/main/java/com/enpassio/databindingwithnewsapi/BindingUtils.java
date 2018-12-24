package com.enpassio.databindingwithnewsapi;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public final class BindingUtils {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    public static String[] splitDateAndTime(String dateAndTime) {
        return TextUtils.isEmpty(dateAndTime) ? null : dateAndTime.split("T");
    }
}
