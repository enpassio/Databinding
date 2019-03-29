package com.enpassio.twowaydatabinding.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.enpassio.twowaydatabinding.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class BindingUtils {

    @BindingAdapter("genderDrawable")
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

    @BindingAdapter("stateDrawable")
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

        //Filter categories whose values are true
        ArrayList<String> filteredList = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : categories.entrySet()) {
            if(entry.getValue()) {
                filteredList.add(entry.getKey());
            }
        }

        //Append categories in the list with comma as separator
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = filteredList.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            sb.append(element);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    @BindingAdapter("visible")
    public static void setVisible(View view, boolean visible){
        if(visible){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
