package com.enpassio.twowaydatabinding.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.enpassio.twowaydatabinding.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class BindingUtils {

    private static final int UNISEX = 0;
    private static final int GIRL = 1;
    private static final int BOY = 2;

    @BindingAdapter("genderDrawable")
    public static void getGenderDrawable(ImageView imageView, int position){
        int resourceId;
        switch(position){
            case UNISEX: {
                resourceId = R.drawable.ic_rainbow;
                break;
            }
            case GIRL: {
                resourceId = R.drawable.ic_girl;
                break;
            }
            case BOY: {
                resourceId = R.drawable.ic_boy;
                break;
            }
            default:{
                resourceId = R.drawable.ic_rainbow;
            }
        }
        imageView.setImageResource(resourceId);
    }

    @BindingAdapter("stateDrawable")
    public static void getStateDrawable(ImageView imageView, int buttonId){
        switch(buttonId){
            case R.id.radioBtn_bought: {
                imageView.setImageResource(R.drawable.ic_money);
                break;
            }
            case R.id.radioBtn_received: {
                imageView.setImageResource(R.drawable.ic_gift);
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
