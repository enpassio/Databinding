package com.enpassio.databindingwithnewsapi.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.enpassio.databindingwithnewsapi.R;

public final class BindingUtils {

    @BindingAdapter("imageSrc")
    public static void loadImage(ImageView view, String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .error(R.drawable.image_not_found)
                .into(view);
    }

    @BindingAdapter("visible")
    public static void setVisible(View view, boolean visible){
        if(visible){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static String[] splitDateAndTime(String dateAndTime) {
        return TextUtils.isEmpty(dateAndTime) ? null : dateAndTime.split("T");
    }

    public static String formatAuthor(String author) {
        /*If author is null or contains the text null(which seems to be the case here),
        return an empty string, otherwise add "By" to the author*/
        return (author == null || author.equals("null")) ? "" : "By " + author;
    }

    public static String hideCharCount(String content) {
        /*Contents of articles in NewsApi are limited and they finish by
        a word count like: "...[+1600 chars]". This method is for hiding
        those last brackets*/

        int lastIndex = content.lastIndexOf("[+");

        /*If lastIndex is positive, then create a substring up to that point.
        If lastName is not positive, that means those characters are not found,
        then return the whole content*/
        return lastIndex > 0 ? content.substring(0, lastIndex) : content;
    }
}
