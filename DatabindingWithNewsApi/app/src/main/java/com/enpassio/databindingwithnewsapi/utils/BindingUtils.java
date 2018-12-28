package com.enpassio.databindingwithnewsapi.utils;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.enpassio.databindingwithnewsapi.R;

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

    public static String formatAuthor(String author) {
        /*If author is null or contains the text null(which seems to be the case here),
        return an empty string, otherwise add "By" to the author*/
        return (author == null || author.equals("null")) ? "" : "By " + author;
    }
}
