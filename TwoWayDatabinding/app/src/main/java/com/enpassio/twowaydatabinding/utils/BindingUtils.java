package com.enpassio.twowaydatabinding.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.enpassio.twowaydatabinding.R;

import java.util.Map;

public final class BindingUtils {

    @BindingAdapter("app:genderDrawable")
    public static void getGenderDrawable(ImageView imageView, int position){
        switch(position){
            case 0: {
                imageView.setImageResource(R.drawable.ic_rainbow);
                break;
            }
            case 1: {
                imageView.setImageDrawable(new ColorDrawable(
                        ContextCompat.getColor(imageView.getContext(), R.color.color_girls)));
                break;
            }
            case 2: {
                imageView.setImageDrawable(new ColorDrawable(
                        ContextCompat.getColor(imageView.getContext(), R.color.color_boys)));
                break;
            }
        }
    }

    @BindingAdapter("app:stateDrawable")
    public static void getStateDrawable(ImageView imageView, int buttonId){
        switch(buttonId){
            case R.id.radioBtn_new: {
                imageView.setImageResource(R.drawable.ic_gift);
                break;
            }
            case R.id.radioBtn_used: {
                imageView.setImageResource(R.drawable.ic_recycle);
                break;
            }
        }
    }

    public static String attachCategories(Map<String,Boolean> categories){
        if(categories == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : categories.entrySet()) {
            if(entry.getValue()) {
                sb.append(entry.getKey());
                sb.append(", ");
            }
        }
        int length = sb.length();
        sb.delete(length-2, length-1);
        return sb.toString();
    }
}
