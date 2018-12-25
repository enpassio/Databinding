package com.enpassio.databindingwithnewsapi;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

public final class BindingUtils {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .error(R.drawable.image_not_found)
                .into(view);
    }

    public static String[] splitDateAndTime(String dateAndTime) {
        return TextUtils.isEmpty(dateAndTime) ? null : dateAndTime.split("T");
    }
}
