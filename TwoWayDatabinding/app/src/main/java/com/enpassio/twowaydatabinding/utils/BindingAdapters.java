package com.enpassio.twowaydatabinding.utils;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.data.model.Gender;
import com.enpassio.twowaydatabinding.data.model.ProcurementType;

public class BindingAdapters {

    private static final int UNISEX = 0;
    private static final int GIRL = 1;
    private static final int BOY = 2;

    @BindingAdapter("genderDrawable")
    public static void getGenderDrawable(ImageView imageView, Gender gender){
        int resourceId;
        switch(gender.ordinal()){
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
    public static void getStateDrawable(ImageView imageView, ProcurementType procurementType){
        if(procurementType == null) return;
        switch(procurementType){
            case BOUGHT: {
                imageView.setImageResource(R.drawable.ic_money);
                break;
            }
            case RECEIVED: {
                imageView.setImageResource(R.drawable.ic_gift);
                break;
            }
        }
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
