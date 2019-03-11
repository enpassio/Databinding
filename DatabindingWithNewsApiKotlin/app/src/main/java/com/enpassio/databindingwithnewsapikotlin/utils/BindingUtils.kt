@file:JvmName("BindingUtils")
package com.enpassio.databindingwithnewsapikotlin.utils

import android.widget.ImageView
import com.enpassio.databindingwithnewsapikotlin.R


@BindingAdapter("imageSrc")
fun loadImage(view: ImageView, url: String) {
    GlideApp.with(view.context)
        .load(url)
        .error(R.drawable.image_not_found)
        .into(view)
}

fun splitDateAndTime(dateAndTime: String?): List<String>? {
    return dateAndTime?.split("T")
}

fun formatAuthor(author: String?): String {
    /*If author is null or contains the text null(which seems to be the case for NewsApi),
        return an empty string, otherwise add "By" to the author*/
    return if (author.isNullOrBlank() || author == "null") "" else "By $author"
}

fun hideCharCount(content: String): String {
    /*Contents of articles in NewsApi are limited and they finish by
        a word count like: "...[+1600 chars]". This method is for hiding
        those last brackets*/
    val lastIndex = content.lastIndexOf("[+")

    /*If lastIndex is positive, then create a substring up to that point.
        If lastName is not positive, that means those characters are not found,
        then return the whole content*/
    return if (lastIndex > 0) content.substring(0, lastIndex) else content
}